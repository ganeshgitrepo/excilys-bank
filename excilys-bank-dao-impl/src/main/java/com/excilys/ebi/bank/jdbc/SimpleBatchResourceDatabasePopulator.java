package com.excilys.ebi.bank.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptStatementFailedException;
import org.springframework.util.StringUtils;

public class SimpleBatchResourceDatabasePopulator implements DatabasePopulator {

	private static final Log logger = LogFactory.getLog(ResourceDatabasePopulator.class);

	private List<Resource> scripts = new ArrayList<Resource>();

	private String sqlScriptEncoding;

	private boolean continueOnError = false;

	private boolean ignoreFailedDrops = false;

	private int batchSize = 50;

	/**
	 * Add a script to execute to populate the database.
	 * 
	 * @param script
	 *            the path to a SQL script
	 */
	public void addScript(Resource script) {
		this.scripts.add(script);
	}

	/**
	 * Set the scripts to execute to populate the database.
	 * 
	 * @param scripts
	 *            the scripts to execute
	 */
	public void setScripts(Resource[] scripts) {
		this.scripts = Arrays.asList(scripts);
	}

	/**
	 * Specify the encoding for SQL scripts, if different from the platform
	 * encoding. Note setting this property has no effect on added scripts that
	 * are already {@link EncodedResource encoded resources}.
	 * 
	 * @see #addScript(Resource)
	 */
	public void setSqlScriptEncoding(String sqlScriptEncoding) {
		this.sqlScriptEncoding = sqlScriptEncoding;
	}

	/**
	 * Flag to indicate that all failures in SQL should be logged but not cause
	 * a failure. Defaults to false.
	 */
	public void setContinueOnError(boolean continueOnError) {
		this.continueOnError = continueOnError;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/**
	 * Flag to indicate that a failed SQL <code>DROP</code> statement can be
	 * ignored.
	 * <p>
	 * This is useful for non-embedded databases whose SQL dialect does not
	 * support an <code>IF EXISTS</code> clause in a <code>DROP</code>. The
	 * default is false so that if the populator runs accidentally, it will fail
	 * fast when the script starts with a <code>DROP</code>.
	 */
	public void setIgnoreFailedDrops(boolean ignoreFailedDrops) {
		this.ignoreFailedDrops = ignoreFailedDrops;
	}

	public void populate(Connection connection) throws SQLException {
		for (Resource script : this.scripts) {
			try {
				executeSqlScript(connection, applyEncodingIfNecessary(script), this.continueOnError, this.ignoreFailedDrops);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private EncodedResource applyEncodingIfNecessary(Resource script) {
		if (script instanceof EncodedResource) {
			return (EncodedResource) script;
		} else {
			return new EncodedResource(script, this.sqlScriptEncoding);
		}
	}

	/**
	 * Execute the given SQL script.
	 * <p>
	 * The script will normally be loaded by classpath. There should be one
	 * statement per line. Any {@link #setSeparator(String) statement
	 * separators} will be removed.
	 * <p>
	 * <b>Do not use this method to execute DDL if you expect rollback.</b>
	 * 
	 * @param connection
	 *            the JDBC Connection with which to perform JDBC operations
	 * @param resource
	 *            the resource (potentially associated with a specific encoding)
	 *            to load the SQL script from
	 * @param continueOnError
	 *            whether or not to continue without throwing an exception in
	 *            the event of an error
	 * @param ignoreFailedDrops
	 *            whether of not to continue in the event of specifically an
	 *            error on a <code>DROP</code>
	 */
	private void executeSqlScript(Connection connection, EncodedResource resource, boolean continueOnError, boolean ignoreFailedDrops) throws SQLException, IOException {

		if (logger.isInfoEnabled()) {
			logger.info("Executing SQL script from " + resource);
		}

		long startTime = System.currentTimeMillis();
		List<String> statements = IOUtils.readLines(resource.getReader());
		int lineNumber = 0;

		boolean initialAutoCommitState = connection.getAutoCommit();

		connection.setAutoCommit(false);
		Statement stmt = connection.createStatement();
		try {
			for (String statement : statements) {
				lineNumber++;
				try {
					stmt.addBatch(statement);

					if (lineNumber % batchSize == 0) {
						stmt.executeBatch();
						connection.commit();
					}
				} catch (SQLException ex) {
					boolean dropStatement = StringUtils.startsWithIgnoreCase(statement.trim(), "drop");
					if (continueOnError || (dropStatement && ignoreFailedDrops)) {
						if (logger.isDebugEnabled()) {
							logger.debug("Failed to execute SQL script statement at line " + lineNumber + " of resource " + resource + ": " + statement, ex);
						}
					} else {
						throw new ScriptStatementFailedException(statement, lineNumber, resource, ex);
					}
				}
			}
		} finally {
			stmt.executeBatch();
			connection.commit();

			connection.setAutoCommit(initialAutoCommitState);

			try {
				stmt.close();
			} catch (Throwable ex) {
				logger.debug("Could not close JDBC Statement", ex);
			}
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		if (logger.isInfoEnabled()) {
			logger.info("Done executing SQL script from " + resource + " in " + elapsedTime + " ms.");
		}
	}
}

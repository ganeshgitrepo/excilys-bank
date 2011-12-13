package com.excilys.ebi.bank.jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;

public class DriverUnregistringDataSource extends DataSource {

	@Override
	public void close() {
		super.close();
		Driver mySqlDriver;
		try {
			mySqlDriver = DriverManager.getDriver(getUrl());
			DriverManager.deregisterDriver(mySqlDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

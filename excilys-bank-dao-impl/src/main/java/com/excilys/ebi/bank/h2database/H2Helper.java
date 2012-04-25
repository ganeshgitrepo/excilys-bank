/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.bank.h2database;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Helper {

	private Server server;

	public H2Helper() {
		server = new Server();
		try {
			server.runTool("-tcp");
		} catch (SQLException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public void shutDown() {
		server.shutdown();
	}
}

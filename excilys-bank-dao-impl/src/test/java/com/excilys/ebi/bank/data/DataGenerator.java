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
package com.excilys.ebi.bank.data;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DataGenerator {

	private static final int NUMBER_OF_USERS = 100;
	private static final int NUMBER_OF_ACCOUNTS_PER_USER = 3;
	private static final int NUMBER_OF_NON_CARD_OPERATIONS_PER_ACCOUNT = 80;
	private static final int NUMBER_OF_CARD_OPERATIONS_PER_CARD = 80;
	private static final Random RANDOM = new Random();
	private static final DateTime NOW = DateTime.now();
	private static final long LAST_SIX_MONTHS_IN_MILLIS = NOW.getMillis() - NOW.minusMonths(6).getMillis();
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {

		OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(
				"/Users/stephanelandelle/Documents/Techno/dev/workspaces/workspace-excilys/excilys-bank/excilys-bank-dao-impl/src/main/resources/sql/data-h2.sql")));

		try {
			writeln(writer, "INSERT INTO ref_role VALUES ('ROLE_USER', 'user');");
			writeln(writer, "INSERT INTO ref_role VALUES ('ROLE_ADMIN', 'admin');");

			writeln(writer, "INSERT INTO ref_account_category VALUES ('CHECKING', 'Checking');" + LINE_SEPARATOR);
			writeln(writer, "INSERT INTO ref_account_category VALUES ('SAVING', 'Saving');");

			writeln(writer, "INSERT INTO ref_account_type VALUES ('PERSONNAL_CHECKING', 'Personnal checking', 'CHECKING');");
			writeln(writer, "INSERT INTO ref_account_type VALUES ('JOINT_CHECKING', 'Joint checking', 'CHECKING');");
			writeln(writer, "INSERT INTO ref_account_type VALUES ('CEL', 'CEL', 'SAVING');");
			writeln(writer, "INSERT INTO ref_account_type VALUES ('PEL', 'PEL', 'SAVING');");

			writeln(writer, "INSERT INTO ref_card_type VALUES ('CREDIT', 'Credit');");
			writeln(writer, "INSERT INTO ref_card_type VALUES ('DEBIT', 'Debit');");

			writeln(writer, "INSERT INTO ref_operation_status VALUES ('PENDING', 'Pending');");
			writeln(writer, "INSERT INTO ref_operation_status VALUES ('RESOLVED', 'Resolved');");

			writeln(writer, "INSERT INTO ref_operation_type VALUES ('MISC', 'Misc');");
			writeln(writer, "INSERT INTO ref_operation_type VALUES ('CARD', 'Card');");
			writeln(writer, "INSERT INTO ref_operation_type VALUES ('TRANSFER', 'Transfer');");

			int userId = 0;
			int accountId = 0;
			int cardId = 0;
			int operationId = 0;

			for (int i = 0; i < NUMBER_OF_USERS; i++) {
				userId++;

				writeln(writer, "INSERT INTO usr VALUES (" + userId + ", 'name" + userId + "@excilys.com', 'Foo" + userId + "', 'Bar" + userId + "', 'user" + userId + "', 'password"
						+ userId + "');");
				writeln(writer, "INSERT INTO usr_role VALUES (" + userId + ", 'ROLE_USER');");

				for (int j = 0; j < NUMBER_OF_ACCOUNTS_PER_USER; j++) {
					accountId++;

					writeln(writer, "INSERT INTO account VALUES (" + accountId + ", " + getRandomInt(3000, 50000) + ".00, '" + DATE_FORMAT.print(getRandomDateTime()) + "', 'ACC"
							+ accountId + "', 'PERSONNAL_CHECKING');");
					writeln(writer, "INSERT INTO usr_account VALUES (" + userId + ", " + accountId + ");");

					for (int k = 0; k < NUMBER_OF_NON_CARD_OPERATIONS_PER_ACCOUNT; k++) {
						operationId++;
						String status = RANDOM.nextBoolean() ? "PENDING" : "RESOLVED";
						String type = RANDOM.nextBoolean() ? "MISC" : "TRANSFER";
						int amount = getRandomInt(-100, 100);

						writeln(writer, "INSERT INTO operation VALUES (" + operationId + ", " + amount + ".00, '" + DATE_FORMAT.print(getRandomDateTime()) + "', 'cash " + amount + "', "
								+ accountId + ", NULL, '" + status + "', '" + type + "');");
					}

					if (RANDOM.nextBoolean()) {
						cardId++;
						String cardType = RANDOM.nextBoolean() ? "CREDIT" : "DEBIT";

						writeln(writer, "INSERT INTO card VALUES (" + cardId + ", 'CARD" + cardId + "', NULL, NULL, " + accountId + ", '" + cardType + "');");

						// card
						for (int k = 0; k < NUMBER_OF_CARD_OPERATIONS_PER_CARD; k++) {
							operationId++;
							String status = RANDOM.nextBoolean() ? "PENDING" : "RESOLVED";
							int amount = getRandomInt(-100, 100);

							writeln(writer, "INSERT INTO operation VALUES (" + operationId + ", " + amount + ".00, '" + DATE_FORMAT.print(getRandomDateTime()) + "', 'cash " + amount + "', "
									+ accountId + ", " + cardId + ", '" + status + "', 'CARD');");
						}
					}
				}
			}

		} finally {
			writer.close();
		}
	}

	private static long getRandomLong(long min, long max) {
		return (long) (RANDOM.nextDouble() *(max - min)) + min;
	}

	private static int getRandomInt(int min, int max) {
		return RANDOM.nextInt(max - min) + min;
	}

	private static DateTime getRandomDateTime() {
		return NOW.minus(getRandomLong(0, LAST_SIX_MONTHS_IN_MILLIS));
	}

	private static void writeln(OutputStreamWriter writer, String string) throws Exception {
		writer.write(string);
		writer.write(LINE_SEPARATOR);
	}
}

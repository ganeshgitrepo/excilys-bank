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
package com.excilys.ebi.bank.model;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

public class YearMonth {

	private final int year;

	private final int monthOfYear;

	private final Range<DateTime> range;

	public YearMonth(int year, int monthOfYear) {
		this.year = year;
		this.monthOfYear = monthOfYear;

		DateMidnight start = new DateMidnight().withYear(year).withMonthOfYear(monthOfYear).withDayOfMonth(1);
		DateMidnight end = start.plusMonths(1);
		range = new Range<DateTime>(start.toDateTime(), end.toDateTime());
	}

	public int getYear() {
		return year;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public Range<DateTime> getRange() {
		return range;
	}
}

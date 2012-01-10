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

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

public class Calendar implements Serializable {

	private static final long serialVersionUID = 1870354008317144931L;

	private Integer month;

	private Integer year;

	private DateTime selectedMonth;

	private List<DateTime> months = newArrayList();

	public Integer getMonth() {
		return month;
	}

	public Integer getYear() {
		return year;
	}

	public DateTime getSelectedMonth() {
		return selectedMonth;
	}

	public List<DateTime> getMonths() {
		return months;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setSelectedMonth(DateTime selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public void setMonths(List<DateTime> months) {
		this.months = months;
	}
}

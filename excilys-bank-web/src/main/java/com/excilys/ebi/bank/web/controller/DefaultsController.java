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
package com.excilys.ebi.bank.web.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.service.BankService;

@Controller
public class DefaultsController {

	@Autowired
	private BankService bankService;

	@RequestMapping("/private/bank/account/*/operations.html")
	public String displayOperationsWithDefaults() {

		DateTime now = bankService.getDefaultDateTime();

		return new StringBuilder().append("forward:year/").append(now.getYear()).append("/month/").append(now.getMonthOfYear()).append("/operations.html").toString();
	}

	@RequestMapping("/private/bank/account/*/cards/*/operations.html")
	public String displayCardOperationsWithDefaults() {

		DateTime now = bankService.getDefaultDateTime();

		return new StringBuilder().append("forward:year/").append(now.getYear()).append("/month/").append(now.getMonthOfYear()).append("/operations.html").toString();
	}
}

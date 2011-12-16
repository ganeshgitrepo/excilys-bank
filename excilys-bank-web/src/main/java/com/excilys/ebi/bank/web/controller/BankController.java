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

import static com.google.common.collect.Collections2.filter;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.ref.AccountCategory;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;
import com.excilys.ebi.bank.web.security.SecurityUtils;
import com.google.common.base.Predicate;

@Controller
@RequestMapping("/private/bank")
@WebPageModelAttribute(WebPage.ACCOUNTS)
public class BankController {

	@Autowired
	private BankService bankService;

	@RequestMapping("/accounts.html")
	public void home(ModelMap model) {

		List<Account> accounts = bankService.findAccountsByUserFetchCardsOrderByNumberAsc(SecurityUtils.getCurrentUser());

		model.put("checkingAccounts", filterByCategory(accounts, AccountCategory.CHECKING));
		model.put("savingAccounts", filterByCategory(accounts, AccountCategory.SAVING));
	}

	private Collection<Account> filterByCategory(Collection<Account> accounts, final AccountCategory category) {
		return filter(accounts, new Predicate<Account>() {
			@Override
			public boolean apply(Account input) {
				return input.getType().getCategory().getId() == category;
			}
		});
	}
}

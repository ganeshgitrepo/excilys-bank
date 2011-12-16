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
package com.excilys.ebi.bank.web.interceptor.account;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.AnnotatedMethodHandlerInterceptor;

public class AccountModelAttributeHandlerInterceptor extends AnnotatedMethodHandlerInterceptor<AccountModelAttribute> {

	@Autowired
	protected BankService bankService;

	public AccountModelAttributeHandlerInterceptor() {
		super(AccountModelAttribute.class);
	}

	@Override
	protected void postHandleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, ModelAndView modelAndView, Map<String, ?> pathVariables)
			throws Exception {
		exportAccount(modelAndView.getModelMap(), pathVariables);
	}

	private void exportAccount(ModelMap model, Map<String, ?> pathVariables) {

		String accountNumber = getModelOrPathAttribute("accountNumber", model, pathVariables);
		Assert.notNull(accountNumber, "accountNumber required");

		Account account = bankService.findAccountByNumberFetchCardsOrderByNumberAsc(accountNumber);
		model.addAttribute("account", account);
	}
}

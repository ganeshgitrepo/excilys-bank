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
package com.excilys.ebi.bank.web.controller.account.operations;

import java.math.BigDecimal;
import java.util.Map;

import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.interceptor.account.AccountModelAttribute;
import com.excilys.ebi.bank.web.interceptor.calendar.CalendarModelAttribute;
import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;

@Controller
@RequestMapping("/private/bank/account/{accountNumber}/year/{year}/month/{month}")
@WebPageModelAttribute(WebPage.ACCOUNT_OPERATIONS)
public class AccountOperationsController {

	@Autowired
	private BankService bankService;

	@Autowired
	private OperationsTableConverter converter;

	@RequestMapping("/operations.html")
	@AccountModelAttribute
	@CalendarModelAttribute
	public String displayOperations(@PathVariable String accountNumber, @PathVariable int year, @PathVariable int month, ModelMap model) {

		Integer accountId = bankService.findAccountIdByNumber(accountNumber);

		Map<Card, BigDecimal[]> cardSums = bankService.sumResolvedCardOperationsByAccountIdAndYearMonth(accountId, new YearMonth(year, month));
		BigDecimal creditSum = bankService.sumResolvedAmountByAccountIdAndYearMonthAndSign(accountId, new YearMonth(year, month), OperationSign.CREDIT);
		BigDecimal debitSum = bankService.sumResolvedAmountByAccountIdAndYearMonthAndSign(accountId, new YearMonth(year, month), OperationSign.DEBIT);

		model.put("cardSums", cardSums.entrySet());
		model.put("creditSum", creditSum);
		model.put("debitSum", debitSum);

		return "private/bank/account/operations";
	}

	@RequestMapping("/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable paginateOperations(@PathVariable String accountNumber, @PathVariable int year, @PathVariable int month, @PathVariable int page) {

		Integer accountId = bankService.findAccountIdByNumber(accountNumber);

		Page<Operation> operationPage = bankService.findNonCardOperationsByAccountIdAndYearMonth(accountId, new YearMonth(year, month), page);
		return converter.convert(operationPage);
	}
}
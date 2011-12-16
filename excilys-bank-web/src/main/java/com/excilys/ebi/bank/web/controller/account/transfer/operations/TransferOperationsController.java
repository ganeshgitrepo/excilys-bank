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
package com.excilys.ebi.bank.web.controller.account.transfer.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTable;
import com.excilys.ebi.bank.web.controller.account.operations.OperationsTableConverter;
import com.excilys.ebi.bank.web.interceptor.account.AccountModelAttribute;
import com.excilys.ebi.bank.web.interceptor.page.WebPage;
import com.excilys.ebi.bank.web.interceptor.page.WebPageModelAttribute;

@Controller
@RequestMapping("/private/bank/account/{accountNumber}/transfers")
@WebPageModelAttribute(WebPage.TRANSFER_OPERATIONS)
public class TransferOperationsController {

	@Autowired
	protected BankService bankService;

	@Autowired
	private OperationsTableConverter converter;

	@RequestMapping("/operations.html")
	@AccountModelAttribute
	public String resolvedCardOperations(@PathVariable String accountNumber, ModelMap model) {
		return "private/bank/account/transfers/operations";
	}

	@RequestMapping("/page/{page}/operations.json")
	public @ResponseBody
	OperationsTable resolvedOperations(@PathVariable String accountNumber, @PathVariable int page) {

		Integer accountId = bankService.findAccountIdByNumber(accountNumber);
		Page<Operation> operations = bankService.findTransferOperationsByAccountId(accountId, page);

		return converter.convert(operations);
	}
}
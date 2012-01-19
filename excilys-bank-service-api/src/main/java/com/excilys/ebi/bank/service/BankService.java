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
package com.excilys.ebi.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;

import com.excilys.ebi.bank.model.Calendar;
import com.excilys.ebi.bank.model.YearMonth;
import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;

public interface BankService {

	Integer findAccountIdByNumber(String accountNumber);

	Integer findCardIdByNumber(String cardNumber);

	List<Account> findAccountsByUser(User user);

	List<Account> findAccountsByUserFetchCardsOrderByNumberAsc(User user);

	Account findAccountByNumberFetchCards(String accountNumber);

	Page<Operation> findNonCardOperationsByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth, int page);

	Map<Card, BigDecimal[]> sumResolvedCardOperationsByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth);

	BigDecimal sumResolvedAmountByAccountIdAndYearMonthAndSign(Integer accountId, YearMonth yearMonth, OperationSign sign);

	Page<Operation> findResolvedCardOperationsByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth, int page);

	BigDecimal sumResolvedCardAmountByAccountIdAndYearMonthAndSign(Integer accountId, YearMonth yearMonth, OperationSign sign);

	Page<Operation> findResolvedCardOperationsByCardIdAndYearMonth(Integer cardId, YearMonth yearMonth, int page);

	BigDecimal sumResolvedCardAmountByCardIdAndYearMonthAndSign(Integer cardId, YearMonth yearMonth, OperationSign sign);

	Page<Operation> findPendingCardOperationsByAccountId(Integer accountId, int page);

	BigDecimal sumPendingCardAmountByAccountIdAndSign(Integer accountId, OperationSign sign);

	Page<Operation> findPendingCardOperationsByCardId(Integer cardId, int page);

	BigDecimal sumPendingCardAmountByCardIdAndSign(Integer cardId, OperationSign sign);

	Page<Operation> findTransferOperationsByAccountId(Integer accountId, int page);

	void performTransfer(Integer debitedAccountId, Integer creditedAccountId, BigDecimal amount) throws UnsufficientBalanceException;

	long countUsers();

	long countAccounts();

	long countOperations();

	Calendar getCalendar(Integer year, Integer month);

	DateTime getDefaultDateTime();
}

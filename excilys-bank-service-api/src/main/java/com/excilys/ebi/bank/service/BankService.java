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

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.springframework.data.domain.Page;

import com.excilys.ebi.bank.model.Calendar;
import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;

public interface BankService {

	Integer findAccountIdByNumber(@NotNull @Length(min = 1) String accountNumber);

	Integer findCardIdByNumber(@NotNull @Length(min = 1) String cardNumber);

	List<Account> findAccountsByUser(@NotNull User user);

	List<Account> findAccountsByUserFetchCardsOrderByNumberAsc(@NotNull User user);

	Account findAccountByNumberFetchCards(@NotNull @Length(min = 1) String accountNumber);

	Page<Operation> findNonCardOperationsByAccountIdAndYearMonth(@NotNull Integer accountId, @NotNull YearMonth yearMonth, int page);

	Map<Card, BigDecimal[]> sumResolvedCardOperationsByAccountIdAndYearMonth(@NotNull Integer accountId, @NotNull YearMonth yearMonth);

	BigDecimal sumResolvedAmountByAccountIdAndYearMonthAndSign(@NotNull Integer accountId, @NotNull YearMonth yearMonth, @NotNull OperationSign sign);

	Page<Operation> findResolvedCardOperationsByAccountIdAndYearMonth(@NotNull Integer accountId, @NotNull YearMonth yearMonth, int page);

	BigDecimal sumResolvedCardAmountByAccountIdAndYearMonthAndSign(@NotNull Integer accountId, @NotNull YearMonth yearMonth, @NotNull OperationSign sign);

	Page<Operation> findResolvedCardOperationsByCardIdAndYearMonth(@NotNull Integer cardId, @NotNull YearMonth yearMonth, int page);

	BigDecimal sumResolvedCardAmountByCardIdAndYearMonthAndSign(@NotNull Integer cardId, @NotNull YearMonth yearMonth, @NotNull OperationSign sign);

	Page<Operation> findPendingCardOperationsByAccountId(@NotNull Integer accountId, int page);

	BigDecimal sumPendingCardAmountByAccountIdAndSign(@NotNull Integer accountId, @NotNull OperationSign sign);

	Page<Operation> findPendingCardOperationsByCardId(@NotNull Integer cardId, int page);

	BigDecimal sumPendingCardAmountByCardIdAndSign(@NotNull Integer cardId, @NotNull OperationSign sign);

	Page<Operation> findTransferOperationsByAccountId(@NotNull Integer accountId, int page);

	void performTransfer(@NotNull Integer debitedAccountId, @NotNull Integer creditedAccountId, BigDecimal amount) throws UnsufficientBalanceException;

	long countUsers();

	long countAccounts();

	long countOperations();

	Calendar getCalendar(@NotNull Integer year, @NotNull Integer month);

	DateTime getDefaultDateTime();
}

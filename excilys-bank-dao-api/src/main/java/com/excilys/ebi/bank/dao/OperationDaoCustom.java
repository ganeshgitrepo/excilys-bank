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
package com.excilys.ebi.bank.dao;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excilys.ebi.bank.model.YearMonth;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatus;

public interface OperationDaoCustom {

	Page<Operation> findNonCardByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth, Pageable pageable);

	BigDecimal sumResolvedAmountByCardAndYearMonth(Card card, YearMonth yearMonth);

	List<Operation> sumResolvedAmountByAccountIdAndYearMonthAndSignGroupByCard(Integer accountId, YearMonth yearMonth, OperationSign sign);

	BigDecimal sumResolvedAmountByAccountIdAndYearMonthAndSign(Integer accountId, YearMonth yearMonth, OperationSign sign);

	Page<Operation> findCardOperationsByAccountIdAndYearMonthAndStatus(Integer accountId, YearMonth yearMonth, OperationStatus status, Pageable pageable);

	BigDecimal sumCardAmountByAccountIdAndYearMonthAndSignAndStatus(Integer accountId, YearMonth yearMonth, OperationSign sign, OperationStatus status);

	Page<Operation> findCardOperationsByCardIdAndYearMonthAndStatus(Integer cardId, YearMonth yearMonth, OperationStatus status, Pageable pageable);

	BigDecimal sumCardAmountByCardIdAndYearMonthAndSignAndStatus(Integer cardId, YearMonth yearMonth, OperationSign sign, OperationStatus status);

	Page<Operation> findTransferByAccountId(Integer accountId, Pageable pageable);

	DateTime getLastOperationDate();
}

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
package com.excilys.ebi.bank.dao.impl;

import static com.excilys.ebi.bank.model.entity.QOperation.operation;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.YearMonth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.excilys.ebi.bank.dao.OperationDaoCustom;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.QOperation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatus;
import com.excilys.ebi.bank.model.entity.ref.OperationType;
import com.google.common.base.Function;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class OperationDaoImpl extends QueryDslRepositorySupport implements OperationDaoCustom {

	public OperationDaoImpl() {
		super(Operation.class);
	}

	protected JPQLQuery applyPagination(JPQLQuery query, Pageable pageable) {
		return pageable != null ? query.offset(pageable.getOffset()).limit(pageable.getPageSize()) : query;
	}

	@Override
	public Page<Operation> findNonCardByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth, Pageable pageable) {

		BooleanExpression predicate = operation.type.id.ne(OperationType.CARD).and(operation.account.id.eq(accountId));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		JPQLQuery countQuery = from(operation).where(predicate);
		JPQLQuery query = applyPagination(from(operation).where(predicate).orderBy(operation.date.desc()), pageable);

		return buildPage(countQuery, query, pageable);
	}

	@Override
	public List<Operation> sumResolvedAmountByAccountIdAndYearMonthAndSignGroupByCard(Integer accountId, YearMonth yearMonth, OperationSign sign) {

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(operation.status.id.eq(OperationStatus.RESOLVED)).and(operation.account.id.eq(accountId))
				.and(buildOperationSignPredicate(sign));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		List<Tuple> tuples = from(operation).where(predicate).groupBy(operation.card.id).list(new QTuple(operation.amount.sum(), operation.card.id));

		return newArrayList(transform(tuples, new Function<Tuple, Operation>() {
			@Override
			public Operation apply(Tuple input) {
				return Operation.newOperation().withAmount(input.get(operation.amount.sum())).withCard(Card.newCardBuilder().withId(input.get(operation.card.id)).build()).build();
			}
		}));
	}

	@Override
	public BigDecimal sumResolvedAmountByCardAndYearMonth(Card card, YearMonth yearMonth) {

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(operation.status.id.eq(OperationStatus.RESOLVED)).and(operation.card.eq(card));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		return from(operation).where(predicate).uniqueResult(operation.amount.sum());
	}

	private Predicate buildOperationSignPredicate(OperationSign sign) {
		return sign == OperationSign.CREDIT ? operation.amount.gt(BigDecimal.ZERO) : operation.amount.lt(BigDecimal.ZERO);
	}

	@Override
	public BigDecimal sumResolvedAmountByAccountIdAndYearMonthAndSign(Integer accountId, YearMonth yearMonth, OperationSign sign) {

		BooleanExpression predicate = operation.account.id.eq(accountId).and(buildOperationSignPredicate(sign)).and(operation.status.id.eq(OperationStatus.RESOLVED));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		return from(operation).where(predicate).uniqueResult(operation.amount.sum());
	}

	@Override
	public Page<Operation> findCardOperationsByAccountIdAndYearMonthAndStatus(Integer accountId, YearMonth yearMonth, OperationStatus status, Pageable pageable) {

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(operation.account.id.eq(accountId)).and(operation.status.id.eq(status));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		JPQLQuery countQuery = from(operation).where(predicate);
		JPQLQuery query = applyPagination(from(operation).where(predicate).orderBy(operation.date.desc()), pageable);

		return buildPage(countQuery, query, pageable);
	}

	@Override
	public BigDecimal sumCardAmountByAccountIdAndYearMonthAndSignAndStatus(Integer accountId, YearMonth yearMonth, OperationSign sign, OperationStatus status) {

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(operation.account.id.eq(accountId)).and(buildOperationSignPredicate(sign))
				.and(operation.status.id.eq(status));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		return from(operation).where(predicate).uniqueResult(operation.amount.sum());
	}

	@Override
	public Page<Operation> findCardOperationsByCardIdAndYearMonthAndStatus(Integer cardId, YearMonth yearMonth, OperationStatus status, Pageable pageable) {

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(operation.card.id.eq(cardId)).and(operation.status.id.eq(status));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		JPQLQuery countQuery = from(operation).where(predicate);
		JPQLQuery query = applyPagination(from(operation).where(predicate).orderBy(operation.date.desc()), pageable);

		return buildPage(countQuery, query, pageable);
	}

	@Override
	public BigDecimal sumCardAmountByCardIdAndYearMonthAndSignAndStatus(Integer cardId, YearMonth yearMonth, OperationSign sign, OperationStatus status) {

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(operation.card.id.eq(cardId)).and(buildOperationSignPredicate(sign))
				.and(operation.status.id.eq(status));
		predicate = addOperationYearMonthExpression(predicate, operation, yearMonth);

		return from(operation).where(predicate).uniqueResult(operation.amount.sum());
	}

	@Override
	public Page<Operation> findTransferByAccountId(Integer accountId, Pageable pageable) {

		BooleanExpression predicate = operation.type.id.eq(OperationType.TRANSFER).and(operation.account.id.eq(accountId));

		JPQLQuery countQuery = from(operation).where(predicate);
		JPQLQuery query = applyPagination(from(operation).where(predicate).orderBy(operation.date.desc()), pageable);

		return buildPage(countQuery, query, pageable);
	}

	private BooleanExpression addOperationYearMonthExpression(BooleanExpression predicate, QOperation operation, YearMonth yearMonth) {

		if (yearMonth == null)
			return predicate;
		else {
			Interval range = yearMonth.toInterval();
			return predicate.and(operation.date.between(range.getStart(), range.getEnd()));
		}
	}

	private Page<Operation> buildPage(JPQLQuery countQuery, JPQLQuery query, Pageable pageable) {

		long count = countQuery.count();
		return count > 0 ? new PageImpl<Operation>(query.list(operation), pageable, count) : new PageImpl<Operation>(new ArrayList<Operation>(), pageable, 0);
	}

	@Override
	public DateTime getLastOperationDate() {
		return from(operation).uniqueResult(operation.date.max());
	}
}

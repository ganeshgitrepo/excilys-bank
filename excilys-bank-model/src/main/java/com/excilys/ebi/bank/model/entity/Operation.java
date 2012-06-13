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
package com.excilys.ebi.bank.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatusRef;
import com.excilys.ebi.bank.model.entity.ref.OperationTypeRef;

@SuppressWarnings("serial")
@Entity
@Table(name = "OPERATION")
@Getter
@Setter
@EqualsAndHashCode(of = { "account", "date" }, doNotUseGetters = true)
public class Operation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 20)
	private Integer id;

	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;

	@Column(name = "NAME", nullable = false, length = 20)
	private String name;

	@Column(name = "DATE", nullable = false, updatable = false)
	private DateTime date;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT", nullable = false, updatable = false)
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD", nullable = true, updatable = false)
	private Card card;

	@ManyToOne(optional = false)
	@JoinColumn(name = "STATUS", nullable = false)
	private OperationStatusRef status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TYPE", nullable = false)
	private OperationTypeRef type;

	public OperationSign getSign() {
		return OperationSign.getSign(amount);
	}

	public static Builder newOperation() {
		return new Builder();
	}

	public static class Builder {

		private Operation operation = new Operation();

		public Builder withId(Integer id) {
			operation.id = id;
			return this;
		}

		public Builder withAmount(BigDecimal amount) {
			operation.amount = amount;
			return this;
		}

		public Builder withName(String name) {
			operation.name = name;
			return this;
		}

		public Builder withDate(DateTime date) {
			operation.date = date;
			return this;
		}

		public Builder withAccount(Account account) {
			operation.account = account;
			return this;
		}

		public Builder withCard(Card card) {
			operation.card = card;
			return this;
		}

		public Builder withStatus(OperationStatusRef status) {
			operation.status = status;
			return this;
		}

		public Builder withType(OperationTypeRef type) {
			operation.type = type;
			return this;
		}

		public Operation build() {
			return operation;
		}
	}
}

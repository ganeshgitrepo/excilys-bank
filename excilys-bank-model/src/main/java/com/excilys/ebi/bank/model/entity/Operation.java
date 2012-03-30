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

import org.joda.time.DateTime;

import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatusRef;
import com.excilys.ebi.bank.model.entity.ref.OperationTypeRef;

@Entity
@Table(name = "OPERATION")
public class Operation implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2903197989437525974L;

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

	public OperationSign getSign() {
		return OperationSign.getSign(amount);
	}

	public Integer getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}

	public DateTime getDate() {
		return date;
	}

	public Account getAccount() {
		return account;
	}

	public Card getCard() {
		return card;
	}

	public OperationStatusRef getStatus() {
		return status;
	}

	public OperationTypeRef getType() {
		return type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setStatus(OperationStatusRef status) {
		this.status = status;
	}

	public void setType(OperationTypeRef type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
}

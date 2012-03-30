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

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.excilys.ebi.bank.model.entity.ref.CardTypeRef;

@Entity
@Table(name = "CARD")
@SuppressWarnings("serial")
public class Card implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 20)
	private Integer id;

	@Column(name = "NUMBER", nullable = false, unique = true, length = 20)
	private String number;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TYPE", nullable = false, updatable = false)
	private CardTypeRef type;

	@Column(name = "PENDING")
	private BigDecimal pending;

	@Column(name = "PENDING_DATE")
	private DateTime pendingDate;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT", nullable = false, updatable = false)
	private Account account;

	@OneToMany(mappedBy = "card", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Operation> operations = newArrayList();

	public static Builder newCardBuilder() {
		return new Builder();
	}

	public static class Builder {

		private Card card = new Card();

		public Builder withId(Integer id) {
			card.id = id;
			return this;
		}

		public Builder withNumber(String number) {
			card.number = number;
			return this;
		}

		public Builder withType(CardTypeRef type) {
			card.type = type;
			return this;
		}

		public Builder withPending(BigDecimal pending) {
			card.pending = pending;
			return this;
		}

		public Builder withPendingDate(DateTime pendingDate) {
			card.pendingDate = pendingDate;
			return this;
		}

		public Builder withAccount(Account account) {
			card.account = account;
			return this;
		}

		public Builder withOperations(List<Operation> operations) {
			card.operations = operations;
			return this;
		}

		public Card build() {
			return card;
		}
	}

	public Integer getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public CardTypeRef getType() {
		return type;
	}

	public BigDecimal getPending() {
		return pending;
	}

	public DateTime getPendingDate() {
		return pendingDate;
	}

	public Account getAccount() {
		return account;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setType(CardTypeRef type) {
		this.type = type;
	}

	public void setPending(BigDecimal pending) {
		this.pending = pending;
	}

	public void setPendingDate(DateTime pendingDate) {
		this.pendingDate = pendingDate;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Card other = (Card) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

}

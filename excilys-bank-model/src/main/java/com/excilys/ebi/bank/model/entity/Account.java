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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import com.excilys.ebi.bank.model.entity.ref.AccountTypeRef;

@SuppressWarnings("serial")
@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@EqualsAndHashCode(of = "number", doNotUseGetters = true)
public class Account implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 20)
	private Integer id;

	@Column(name = "NUMBER", nullable = false, unique = true, length = 20)
	private String number;

	@Column(name = "BALANCE", nullable = false)
	private BigDecimal balance;

	@Column(name = "BALANCE_DATE")
	private DateTime balanceDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TYPE", nullable = false, updatable = false)
	private AccountTypeRef type;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "accounts")
	private List<User> users = newArrayList();

	@OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Operation> operations = newArrayList();

	@OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	@OrderBy("number")
	private List<Card> cards = newArrayList();

	public BigDecimal getTotalPending() {

		BigDecimal totalPending = BigDecimal.valueOf(0.0);

		for (Card card : cards) {
			if (card.getPending() != null) {
				totalPending = totalPending.add(card.getPending());
			}
		}
		return totalPending;
	}

	public BigDecimal getEstimatedBalance() {
		return balance.add(getTotalPending());
	}
}

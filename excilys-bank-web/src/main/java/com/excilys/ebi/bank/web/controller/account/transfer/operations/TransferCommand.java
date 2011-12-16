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

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferCommand implements Serializable {

	private static final long serialVersionUID = -1733253994577062650L;

	@NotNull
	private String debitedAccountNumber;

	@NotNull
	private String creditedAccountNumber;

	@Min(10)
	private BigDecimal amount;

	public String getDebitedAccountNumber() {
		return debitedAccountNumber;
	}

	public String getCreditedAccountNumber() {
		return creditedAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setDebitedAccountNumber(String debitedAccountNumber) {
		this.debitedAccountNumber = debitedAccountNumber;
	}

	public void setCreditedAccountNumber(String creditedAccountNumber) {
		this.creditedAccountNumber = creditedAccountNumber;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}

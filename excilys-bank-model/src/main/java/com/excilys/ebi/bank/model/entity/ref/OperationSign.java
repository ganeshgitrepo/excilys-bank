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
package com.excilys.ebi.bank.model.entity.ref;

import java.math.BigDecimal;

public enum OperationSign {

	CREDIT, DEBIT;

	public static OperationSign getSign(BigDecimal amount) {
		return isCredit(amount) ? CREDIT : DEBIT;
	}

	public static boolean isCredit(BigDecimal amount) {
		return amount == null || amount.compareTo(BigDecimal.ZERO) >= 0;
	}
}

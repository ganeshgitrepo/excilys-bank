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
package com.excilys.ebi.bank.web.interceptor.page;

public enum WebPage {

	ROOT(null, null),

	BANK(ROOT, "page.bank.title"),

	ACCOUNTS(BANK, null),

	ACCOUNT(ACCOUNTS, null),

	ACCOUNT_OPERATIONS(ACCOUNT, "page.bank.account.operations.title"),

	CARDS(ACCOUNT, null),

	CARD_OPERATIONS(CARDS, "page.bank.account.cards.operations.title"),

	TRANSFERS(ACCOUNT, null),

	TRANSFER_OPERATIONS(TRANSFERS, "page.bank.account.transfers.operations.title"),

	TRANSFER_PERFORM(TRANSFERS, "page.bank.account.transfers.perform.title"),

	ADMIN(ROOT, "page.admin.title");

	public static final String PAGE_MODEL = "page";

	private final WebPage parent;

	private final String titleKey;

	private WebPage(WebPage parent, String titleKey) {
		this.parent = parent;
		this.titleKey = titleKey;
	}

	public boolean isDisplayable() {
		return titleKey != null;
	}

	public boolean hasAncestor(WebPage page) {

		if (this == page) {
			return true;

		} else if (parent == null) {
			return false;

		} else {
			return parent.hasAncestor(page);
		}
	}
}

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

import static com.excilys.ebi.bank.model.entity.QAccount.account;
import static com.excilys.ebi.bank.model.entity.QUser.user;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.excilys.ebi.bank.dao.AccountDaoCustom;
import com.excilys.ebi.bank.model.entity.Account;
import com.excilys.ebi.bank.model.entity.User;

@Repository
public class AccountDaoImpl extends QueryDslRepositorySupport implements AccountDaoCustom {

	@Override
	public List<Account> findByUserFetchCardsOrderByNumberAsc(User user) {
		return from(account).where(account.users.contains(user)).leftJoin(account.cards).fetch().orderBy(account.number.asc()).listDistinct(account);
	}

	@Override
	public long countAccountsByIdAndUserLogin(Integer id, String login) {
		return from(account).innerJoin(account.users, user).where(account.id.eq(id), user.login.eq(login)).countDistinct();
	}
}

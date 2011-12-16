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
package com.excilys.ebi.bank.web.security;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.excilys.ebi.bank.model.entity.User;
import com.excilys.ebi.bank.model.entity.ref.RoleRef;
import com.excilys.ebi.bank.service.UserService;
import com.google.common.base.Function;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByLoginFetchRoles(username);

		if (user == null) {
			throw new UsernameNotFoundException(username + " : cet utilisateur n'existe pas.");
		}

		return createUserDetails(user);
	}

	private UserDetails createUserDetails(User user) {

		List<GrantedAuthority> authorities = transform(user.getRoles(), new Function<RoleRef, GrantedAuthority>() {
			@Override
			public GrantedAuthority apply(RoleRef input) {
				return new SimpleGrantedAuthority(input.getId().name());
			}
		});

		return new CustomUserDetails(user, newArrayList(authorities));
	}
}
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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.excilys.ebi.bank.model.entity.ref.RoleRef;

@Entity
@Table(name = "USR")
@Getter
@Setter
@EqualsAndHashCode(of = "login", doNotUseGetters = true)
public class User implements Serializable {

	private static final long serialVersionUID = -3337363339851986363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "LOGIN", nullable = false, unique = true, length = 30)
	private String login;

	@Column(name = "FIRSTNAME", nullable = false, length = 30)
	private String firstName;

	@Column(name = "LASTNAME", nullable = false, length = 30)
	private String lastName;

	@Column(name = "PASSWORD", nullable = false, length = 30)
	private String password;

	@Column(name = "EMAIL", nullable = false, length = 30)
	private String email;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USR_ACCOUNT", joinColumns = { @JoinColumn(name = "USR_ID") }, inverseJoinColumns = { @JoinColumn(name = "ACCOUNT_ID") })
	private List<Account> accounts = newArrayList();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USR_ROLE", joinColumns = { @JoinColumn(name = "USR_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<RoleRef> roles = newArrayList();
}

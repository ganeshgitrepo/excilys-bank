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
package com.excilys.ebi.bank.hibernate;

import java.util.Map;

import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class ExtendedHibernateJpaVendorAdapter extends HibernateJpaVendorAdapter {

	private Map<String, Object> jpaPropertyMap;

	@Override
	public Map<String, Object> getJpaPropertyMap() {
		return jpaPropertyMap;
	}

	public void setJpaPropertyMap(Map<String, Object> jpaPropertyMap) {
		this.jpaPropertyMap = jpaPropertyMap;
	}
}

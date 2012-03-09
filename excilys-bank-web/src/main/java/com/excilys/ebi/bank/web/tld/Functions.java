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
package com.excilys.ebi.bank.web.tld;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections15.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.excilys.ebi.bank.web.interceptor.page.WebPage;

public class Functions {

	public static String url(String url) {
		// don't touch absolute URLs
		if (UrlUtils.isAbsoluteUrl(url))
			return url;

		// normalize relative URLs against a context root
		if (url.startsWith("/"))
			return (ctx() + url);
		else
			return url;
	}

	public static String ctx() {

		HttpServletRequest request = ServletRequestAttributes.class.cast(RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getContextPath();
	}

	public static String amount(BigDecimal amount) {

		StringBuilder buf = new StringBuilder();
		buf.append("<span class=\"figure ").append(amount.compareTo(BigDecimal.valueOf(0.0)) >= 0 ? "green" : "red").append("\">").append(amount).append("</span>");
		return buf.toString();
	}

	public static String ifThenElse(boolean when, String then, String otherwise) {
		return when ? then : otherwise;
	}

	public static boolean hasAncestor(WebPage page, WebPage ancestor) {
		return page.hasAncestor(ancestor);
	}

	public static int year(DateTime dateTime) {
		return dateTime.year().get();
	}

	public static int monthOfYear(DateTime dateTime) {
		return dateTime.monthOfYear().get();
	}

	public static String monthOfYearAsText(DateTime dateTime) {
		return dateTime.monthOfYear().getAsText();
	}

	public static int size(Object object) {
		return CollectionUtils.size(object);
	}
}

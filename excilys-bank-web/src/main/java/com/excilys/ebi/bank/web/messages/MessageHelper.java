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
package com.excilys.ebi.bank.web.messages;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MessageHelper {

	public static final String MESSAGES_REDIRECT_ATTRIBUTE_NAME = "messages";

	private MessageHelper() {
		throw new UnsupportedOperationException();
	}

	public static void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {

		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>) redirectAttributes.getFlashAttributes().get(MESSAGES_REDIRECT_ATTRIBUTE_NAME);
		if (messages == null) {
			redirectAttributes.addFlashAttribute(MESSAGES_REDIRECT_ATTRIBUTE_NAME, newArrayList(message));
		} else {
			messages.add(message);
		}
	}
}

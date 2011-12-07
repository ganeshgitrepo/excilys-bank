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

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
package com.excilys.ebi.bank.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.ebi.bank.web.messages.Message;
import com.excilys.ebi.bank.web.messages.MessageHelper;
import com.excilys.ebi.bank.web.security.LoginSuccessHandler;

@Controller
public class LoginController {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@RequestMapping("/public/login.html")
	public String login(ModelMap model, HttpSession session) {

		// hack : can't check SecurityContextHolder has login.html is not
		// protected by Spring Security
		if (session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) != null) {
			return "redirect:" + loginSuccessHandler.getHomeUrl();
		}

		return "public/login";
	}

	@RequestMapping("/public/loginFailure.html")
	public String loginFailure(ModelMap model, HttpSession session, HttpServletResponse res, RedirectAttributes redirectAttributes) {

		Exception loginException = Exception.class.cast(session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));

		Message message = handleException(loginException);
		MessageHelper.addFlashMessage(redirectAttributes, message);

		return "redirect:/public/login.html";
	}

	private Message handleException(Exception loginException) {

		if (loginException instanceof BadCredentialsException) {
			return new Message("message.error.login.badCredentials");

		} else {
			Throwable cause = ExceptionUtils.getRootCause(loginException);
			return new Message("message.error.login", cause != null ? cause.getMessage() : loginException.getMessage());
		}
	}
}

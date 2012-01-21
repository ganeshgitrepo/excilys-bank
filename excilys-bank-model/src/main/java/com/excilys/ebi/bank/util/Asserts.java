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
package com.excilys.ebi.bank.util;

import java.util.Collection;
import java.util.Map;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class Asserts {

	public static void isTrue(boolean expression, String messagePattern, Object arg) {
		if (!expression) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void isTrue(boolean expression, String messagePattern, Object arg1, Object arg2) {
		if (!expression) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void isTrue(boolean expression, String messagePattern, Object... args) {
		if (!expression) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void isNull(Object object, String messagePattern, Object arg) {
		if (object != null) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void isNull(Object object, String messagePattern, Object arg1, Object arg2) {
		if (object != null) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void isNull(Object object, String messagePattern, Object... args) {
		if (object != null) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void notNull(Object object, String messagePattern, Object arg) {
		if (object == null) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void notNull(Object object, String messagePattern, Object arg1, Object arg2) {
		if (object == null) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void notNull(Object object, String messagePattern, Object... args) {
		if (object == null) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void hasLength(String text, String messagePattern, Object arg) {
		if (!StringUtils.hasLength(text)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void hasLength(String text, String messagePattern, Object arg1, Object arg2) {
		if (!StringUtils.hasLength(text)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void hasLength(String text, String messagePattern, Object... args) {
		if (!StringUtils.hasLength(text)) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void hasText(String text, String messagePattern, Object arg) {
		if (!StringUtils.hasText(text)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void hasText(String text, String messagePattern, Object arg1, Object arg2) {
		if (!StringUtils.hasText(text)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void hasText(String text, String messagePattern, Object... args) {
		if (!StringUtils.hasText(text)) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void doesNotContain(String textToSearch, String substring, String messagePattern, Object arg) {
		if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.indexOf(substring) != -1) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void doesNotContain(String textToSearch, String substring, String messagePattern, Object arg1, Object arg2) {
		if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.indexOf(substring) != -1) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void doesNotContain(String textToSearch, String substring, String messagePattern, Object... args) {
		if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.indexOf(substring) != -1) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void notEmpty(Object[] array, String messagePattern, Object arg) {
		if (ObjectUtils.isEmpty(array)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void notEmpty(Object[] array, String messagePattern, Object arg1, Object arg2) {
		if (ObjectUtils.isEmpty(array)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void notEmpty(Object[] array, String messagePattern, Object... args) {
		if (ObjectUtils.isEmpty(array)) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void noNullElements(Object[] array, String messagePattern, Object arg) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
				}
			}
		}
	}

	public static void noNullElements(Object[] array, String messagePattern, Object arg1, Object arg2) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
				}
			}
		}
	}

	public static void noNullElements(Object[] array, String messagePattern, Object... args) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
				}
			}
		}
	}

	public static void notEmpty(Collection<?> collection, String messagePattern, Object arg) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void notEmpty(Collection<?> collection, String messagePattern, Object arg1, Object arg2) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void notEmpty(Collection<?> collection, String messagePattern, Object... args) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void notEmpty(Map<?, ?> map, String messagePattern, Object arg) {
		if (CollectionUtils.isEmpty(map)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void notEmpty(Map<?, ?> map, String messagePattern, Object arg1, Object arg2) {
		if (CollectionUtils.isEmpty(map)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void notEmpty(Map<?, ?> map, String messagePattern, Object... args) {
		if (CollectionUtils.isEmpty(map)) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}

	public static void isInstanceOf(Class<?> type, Object obj, String messagePattern, Object arg) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage() + " Object of class [" + (obj != null ? obj.getClass().getName() : "null")
					+ "] must be an instance of " + type);
		}
	}

	public static void isInstanceOf(Class<?> type, Object obj, String messagePattern, Object arg1, Object arg2) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage() + " Object of class ["
					+ (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
		}
	}

	public static void isInstanceOf(Class<?> type, Object obj, String messagePattern, Object... args) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage() + " Object of class ["
					+ (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
		}
	}

	public static void isAssignable(Class<?> superType, Class<?> subType, String messagePattern, Object arg) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg).getMessage() + subType + " is not assignable to " + superType);
		}
	}

	public static void isAssignable(Class<?> superType, Class<?> subType, String messagePattern, Object arg1, Object arg2) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage() + subType + " is not assignable to " + superType);
		}
	}

	public static void isAssignable(Class<?> superType, Class<?> subType, String messagePattern, Object... args) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(MessageFormatter.arrayFormat(messagePattern, args).getMessage() + subType + " is not assignable to " + superType);
		}
	}

	public static void state(boolean expression, String messagePattern, Object arg) {
		if (!expression) {
			throw new IllegalStateException(MessageFormatter.format(messagePattern, arg).getMessage());
		}
	}

	public static void state(boolean expression, String messagePattern, Object arg1, Object arg2) {
		if (!expression) {
			throw new IllegalStateException(MessageFormatter.format(messagePattern, arg1, arg2).getMessage());
		}
	}

	public static void state(boolean expression, String messagePattern, Object... args) {
		if (!expression) {
			throw new IllegalStateException(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
		}
	}
}

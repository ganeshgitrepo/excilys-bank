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
package com.excilys.ebi.bank.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.excilys.ebi.bank.model.IConstants;
import com.excilys.ebi.bank.service.AnnotationScanner;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;

@Component
public class AnnotationScannerImpl implements AnnotationScanner {

	@Override
	@Cacheable(cacheName = IConstants.Cache.ANNOTATION_SCANNER_CACHE, keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"))
	public <A extends Annotation> A getMethodOrTypeAnnotation(Class<A> annotationType, Method method, Class<?> type) {

		A annotation = AnnotationUtils.findAnnotation(method, annotationType);
		if (annotation == null) {
			annotation = AnnotationUtils.findAnnotation(type, annotationType);
		}

		return annotation;
	}
}

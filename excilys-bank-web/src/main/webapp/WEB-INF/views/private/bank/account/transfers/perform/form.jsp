<%--

    Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    		http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="block">
	<div class="body">
		<form:form commandName="transferCommand" action="perform.html">
			<fieldset>
				<legend><spring:message code="transfers.form.title" /></legend>
				<ol>
					<li>
						<label for="debitedAccountNumber"><spring:message code="transfers.form.debited" /></label>
						<form:select path="debitedAccountNumber">
							<c:forEach items="${debitableAccounts}" var="account">
								<form:option value="${account.number}">${account.type.id} ${account.number} (${account.balance})</form:option>
							</c:forEach>
						</form:select>
						<form:errors path="debitedAccountNumber" />
					</li>
					<li>
						<label for="creditedAccountNumber"><spring:message code="transfers.form.credited" /></label>
						<form:select path="creditedAccountNumber">
							<c:forEach items="${creditableAccounts}" var="account">
								<form:option value="${account.number}">${account.type.id} ${account.number} (${account.balance})</form:option>
							</c:forEach>
						</form:select>
						<form:errors path="creditedAccountNumber" />
					</li>
					<li>
						<label for="amount"><spring:message code="transfers.form.amount" /></label>
						<form:input path="amount" />
						<form:errors path="amount" />
					</li>
				</ol>
				<ol>
					<li class="center">
						<input type="submit" class="submit" value="Perform" />
					</li>
				</ol>
			</fieldset>
		</form:form>
		<div class="cb"></div>
	</div>
</div>


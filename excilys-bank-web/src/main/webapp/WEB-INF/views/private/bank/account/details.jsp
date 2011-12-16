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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<div class="block">
	<div class="body">
		<div class="left">
			<div class="title">
				<span class="icon bank left"></span>
				<span class="iconLabel left">
					${account.type.id}
					<br />
					${account.number}
				</span>
				<div class="cb"></div>
			</div>
		</div>
		<div class="right">
			<table class="accountDetails">
				<tbody>
					<tr>
						<td>
							<spring:message code="accounts.details.balance" />
							<joda:format value="${account.balanceDate}" pattern="MM/dd/yyyy"/>
						</td>
						<td class="amount">${bk:amount(account.balance)} <spring:message code="common.euro" /></td>
					</tr>
					<tr>
						<td><spring:message code="accounts.details.pending" /></td>
						<td class="amount">${bk:amount(account.totalPending)} <spring:message code="common.euro" /></td>
					</tr>
					<tr>
						<td><spring:message code="accounts.details.estimatedBalance" /></td>
						<td class="amount">${bk:amount(account.estimatedBalance)} <spring:message code="common.euro" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="cb"></div>
	</div>
</div>








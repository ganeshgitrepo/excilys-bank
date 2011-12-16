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
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<ul class="tabs">
	<c:choose>
		<c:when test="${bk:hasAncestor(page, 'TRANSFER_OPERATIONS')}">
			<c:set var="active" value="active"/>
		</c:when>
		<c:otherwise>
				<c:set var="active" value=""/>
		</c:otherwise>
	</c:choose>
<%-- 	<li class="${bk:if(page.hasAncestor('TRANSFER_OPERATIONS'), 'active', '')}"> --%>
	<li class="${active}">
		<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/operations.html">
			<spring:message code="transfers.menu.operations" />
		</a>
	</li>
	<c:choose>
		<c:when test="${bk:hasAncestor(page, 'TRANSFER_PERFORM')}">
			<c:set var="active" value="active"/>
		</c:when>
		<c:otherwise>
				<c:set var="active" value=""/>
		</c:otherwise>
	</c:choose>
<%-- 	<li class="${bk:if(page.hasAncestor('TRANSFER_PERFORM'), 'active', '')}"> --%>
	<li class="${active}">
		<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/perform.html">
			<spring:message code="transfers.menu.perform" />
		</a>
	</li>
</ul>
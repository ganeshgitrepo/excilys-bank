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
<div class="submenu ${bk:if(empty account.cards, 'nocards', '')}">
	<div class="block left">
		<div class="body">
			<c:choose>
				<c:when test="${bk:hasAncestor(page, 'ACCOUNT_OPERATIONS')}">
					<c:set var="active" value="active"/>
				</c:when>
				<c:otherwise>
						<c:set var="active" value=""/>
				</c:otherwise>
			</c:choose>
			<div class="title ${active}">
<%-- 			<div class="title ${bk:if(page.hasAncestor('ACCOUNT_OPERATIONS'), 'active', '')}"> --%>
				<a href="${bk:ctx()}/private/bank/account/${account.number}/operations.html">
					<span class="icon operation left"></span>
					<span class="iconLabel left"><spring:message code="accounts.menu.operations" /></span>
				</a>
				<div class="cb"></div>
			</div>
		</div>
	</div>
	<c:if test="${not empty account.cards}">
		<div class="block left">
			<div class="body">
				<c:choose>
					<c:when test="${bk:hasAncestor(page, 'CARDS')}">
						<c:set var="active" value="active"/>
					</c:when>
					<c:otherwise>
							<c:set var="active" value=""/>
					</c:otherwise>
				</c:choose>
					<div class="title ${active}">
<%-- 				<div class="title ${bk:if(page.hasAncestor('CARDS'), 'active', '')}"> --%>
					<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/all/operations.html">
						<span class="icon card left"></span>
						<span class="iconLabel left"><spring:message code="accounts.menu.cards" /></span>
					</a>
					<div class="cb"></div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="block left">
		<div class="body">
				<c:choose>
					<c:when test="${bk:hasAncestor(page, 'TRANSFERS')}">
						<c:set var="active" value="active"/>
					</c:when>
					<c:otherwise>
							<c:set var="active" value=""/>
					</c:otherwise>
				</c:choose>
				<div class="title ${active}">
<%-- 			<div class="title ${bk:if(page.hasAncestor('TRANSFERS'), 'active', '')}"> --%>
				<a href="${bk:ctx()}/private/bank/account/${account.number}/transfers/operations.html">
					<span class="icon transfer left"></span>
					<span class="iconLabel left"><spring:message code="accounts.menu.transfers" /></span>
				</a>
				<div class="cb"></div>
			</div>
		</div>
	</div>
	<div class="cb"></div>
</div>
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
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<div class="bw">
	<div class="bw_header">
		<div class="menu">
			<security:authorize ifAnyGranted="ROLE_USER">
				<c:choose>
					<c:when test="${bk:hasAncestor(page, 'ACCOUNT')}">
						<c:set var="active" value="active"/>
					</c:when>
					<c:otherwise>
							<c:set var="active" value=""/>
					</c:otherwise>
				</c:choose>
				<div class="tab left ${active}">
<%-- 				<div class="tab left ${bk:if(page.hasAncestor('ACCOUNT'), 'active', '')}"> --%>
					<div class="title">
						<a href="${bk:ctx()}/private/bank/accounts.html">
							<span class="icon bank left"></span>
							<span class="iconLabel left">Accounts</span>
						</a>
					</div>
				</div>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN">
				<c:choose>
					<c:when test="${bk:hasAncestor(page, 'ADMIN')}">
						<c:set var="active" value="active"/>
					</c:when>
					<c:otherwise>
							<c:set var="active" value=""/>
					</c:otherwise>
				</c:choose>
				<div class="tab left ${active}">
<%-- 				<div class="tab left ${bk:if(webPage.hasAncestor('ADMIN'), 'active', '')}"> --%>
					<div class="title">
						<a href="${bk:ctx()}/private/admin/admin.html">
							<span class="icon admin left"></span>
							<span class="iconLabel left">Admin</span>
						</a>
					</div>
				</div>
			</security:authorize>
			<div class="after left"></div>
		</div>
		<div class="userInfo">
			<tiles:insertAttribute name="private.user" />
		</div>
	</div>
	<div class="bw_content">
		<tiles:insertAttribute name="private.body" />
	</div>
	<div class="bw_footer"></div>
</div>

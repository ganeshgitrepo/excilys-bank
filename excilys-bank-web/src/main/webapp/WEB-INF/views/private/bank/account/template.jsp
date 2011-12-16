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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<h1>
	<a href="${bk:ctx()}/private/bank/accounts.html">
		<spring:message code="accounts.welcome" />
	</a>
	&gt;
	${account.type.id} ${account.number}
</h1>

<div class="container">
	<tiles:insertAttribute name="account.details"/>
</div>
<div class="container">
	<tiles:insertAttribute name="account.menu"/>
</div>
<div class="container">
	<tiles:insertAttribute name="account.tabs"/>
</div>
<div class="container">
	<tiles:insertAttribute name="account.body"/>
</div>
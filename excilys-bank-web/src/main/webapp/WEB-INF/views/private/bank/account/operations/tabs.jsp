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
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<ul class="tabs">
	<c:forEach items="${calendar.months}" var="month">
		<li class="${bk:if(month eq calendar.selectedMonth, 'active', '')}">
<%-- 			<a href="${bk:ctx()}/private/bank/account/${account.number}/year/${month.year().get()}/month/${month.monthOfYear().get()}/operations.html"> --%>
<%-- ${month.monthOfYear().getAsText()} --%>
			<a href="${bk:ctx()}/private/bank/account/${account.number}/year/${bk:year(month)}/month/${bk:monthOfYear(month)}/operations.html">
				${bk:monthOfYearAsText(month)}
			</a>
		</li>
	</c:forEach>
</ul>
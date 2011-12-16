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
<div class="block">
	<div id="operationsContainer" class="body">
		<table id="operations" class="operations striped">
			<thead>
				<tr>
					<th class="dateHeader"><spring:message code="operations.date" /></th>
					<th><spring:message code="operations.name" /></th>
					<th class="amountHeader"><spring:message code="operations.credit" /></th>
					<th class="amountHeader"><spring:message code="operations.debit" /></th>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th class="amount">${bk:amount(creditSum)}</th>
					<th class="amount">${bk:amount(debitSum)}</th>
				</tr>
			</thead>
			<tbody>
				<bk:ite count="20">
					<tr>
						<td>&nbsp;</td><td></td><td></td><td></td>
					</tr>
				</bk:ite>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<div id="scroller"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
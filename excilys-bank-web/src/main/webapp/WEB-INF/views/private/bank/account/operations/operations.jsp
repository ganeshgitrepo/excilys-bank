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
<div class="block">
	<div id="operationsContainer" class="body">
		<table id="operations" class="operations striped">
			<thead>
				<tr>
					<th class="dateHeader">Date</th>
					<th>Libell�</th>
					<th class="amountHeader">Cr�dit</th>
					<th class="amountHeader">D�bit</th>
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
				<c:forEach items="${cardSums}" var="cardSum">
					<tr>
						<td></td>
						<td>
							<a href="${bk:ctx()}/private/bank/account/${account.number}/cards/${cardSum.key.number}/year/${bk:year(calendar.selectedMonth)}/month/${bk:monthOfYear(calendar.selectedMonth)}/operations.html">
								<span class="icon card left"></span>
								<span class="iconLabel">${cardSum.key.type.id} ${cardSum.key.number}</span>
							</a>
						</td>
						<td class="amount">
							<c:if test="${cardSum.value[0] > 0}">
								${bk:amount(cardSum.value[0])}</td>
							</c:if>
						<td class="amount">
							<c:if test="${cardSum.value[1] > 0}">
								${bk:amount(cardSum.value[1])}
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4">
						<div id="scroller"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
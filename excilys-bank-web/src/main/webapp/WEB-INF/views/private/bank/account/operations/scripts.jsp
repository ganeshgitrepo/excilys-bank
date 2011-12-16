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
<%@ taglib prefix="bk" uri="http://www.excilys.com/jsp/jstl/bank"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script>
function changePage(page) {
<%-- 	paginate("${bk:ctx()}/private/bank/account/${account.number}/year/${calendar.selectedMonth.year().get()}/month/${calendar.selectedMonth.monthOfYear().get()}/page/" + page +"/operations.json");--%>
	paginate("${bk:ctx()}/private/bank/account/${account.number}/year/${bk:year(calendar.selectedMonth)}/month/${bk:monthOfYear(calendar.selectedMonth)}/page/" + page +"/operations.json");
}
changePage(0);
</script>
<script id="tbodyTemplate" type="text/x-jquery-tmpl">
{{each lines}}
	<tr><td class="date">\${\$value.date}</td><td>\${\$value.name}</td>
	{{if \$value.amount >= 0}}
		<td class="amount"><span class="figure green">\${\$value.amount}</span></td><td class="amount"></td>
	{{else}}
		<td class="amount"></td><td class="amount"><span class="figure red">\${\$value.amount}</span></td>
	{{/if}}
	</tr>
{{/each}}
{{each emptyLines}}
	<tr><td>&nbsp;</td><td></td><td></td><td></td></tr>
{{/each}}
</script>
<script id="scrollerTemplate" type="text/x-jquery-tmpl">
	<div class="left">
		<spring:message code="table.scroller.showing" />
		<span id="startIndex">\${startIndex}</span>
		<spring:message code="table.scroller.to" />
		<span id="endIndex">\${endIndex}</span>
		<spring:message code="table.scroller.of" />
		<span id="totalElements">\${totalElements}</span>
		<spring:message code="table.scroller.entries" />
	</div>
	<div class="right">
		<div id="back" class="left">
			{{if hasPreviousPage}}
				<div class="icon back enabled left" onclick="changePage(\${number - 1})"></div>
			{{else}}
				<div class="icon back disabled left"></div>
			{{/if}}
		</div>
		<div class="left">
			<spring:message code="table.scroller.page" />
			<span id="pageIndex">\${pageIndex}</span>
			<spring:message code="table.scroller.of" />
			<span id="totalPages">\${totalPages}</span>
		</div>
		<div id="forward" class="left">
			{{if hasNextPage}}
				<div class="icon forward enabled left" onclick="changePage(\${number + 1})"></div>
			{{else}}
				<div class="icon forward disabled left"></div>
			{{/if}}
		</div>
		<div class="cb"></div>
	</div>
	<div class="cb"></div>
</script>
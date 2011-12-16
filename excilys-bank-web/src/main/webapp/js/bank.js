/*
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function stripe() {
	$(".striped").on("mouseover", "tr", function() {
		$(this).addClass("over");
	}).on('mouseout', 'tr', function() {
		$(this).removeClass("over");
	}).find("tr:nth-child(even)").addClass("alt");
}

function message(str) {
	$.notify_osd.create({
		'text' : str,
		'sticky' : true,
		'dismissable' : true,
		'click_through' : false,
		'opacity_min' : 1,
		'opacity_max' : 1
	});
}

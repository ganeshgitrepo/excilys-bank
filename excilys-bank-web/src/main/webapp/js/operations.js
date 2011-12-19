function paginate(url) {
	$.getJSON(url, function(data) {
		populateTBody(data);
		populateScroller(data);
		stripe();
	});
}

function populateTBody(data) {
	$("#operations").find("tbody").html($("#tbodyTemplate").tmpl(data));
}

function populateScroller(data) {
	$("#scroller").html($("#scrollerTemplate").tmpl(data));
}
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
		'opacity_max' : 1,
	});
}

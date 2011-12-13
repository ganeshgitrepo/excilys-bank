function stripe() {
	var striped = $(".striped");
	striped.find("tr").mouseover(function() {
		$(this).addClass("over");
	}).mouseout(function() {
		$(this).removeClass("over");
	});
	striped.find("tr:nth-child(even)").addClass("alt");
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

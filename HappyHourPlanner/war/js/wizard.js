$('body').on('click','button#add-contact-button',function() { 
	// read contacts and forward
	var rows = $("#mainTable tr:gt(0)");
	rows.each(function(index) {
		if ($.trim($(this).text()).length > 0) {
			//alert($(this).text());
			var child1 = $(this).children(":first").text();
			var child2 = $(this).children(":nth-child(2)").text();
			if ($.trim(child1).length > 0) {
				alert("child1: " + child1);
			}	
			if ($.trim(child2).length > 0) {
				alert("child2: " + child2);
			}
		}
	});
});

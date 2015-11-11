$('body').on('click','button#add-contact-button',function() { 
	// read contacts and forward
	var rows = $("#mainTable tr:gt(0)");
	
	var list = new Array();
	
	rows.each(function(index) {
		if ($.trim($(this).text()).length > 0) {
			//alert($(this).text());
			var name = $.trim($(this).children(":first").text());
			var email = $.trim($(this).children(":nth-child(2)").text());
			if (name.length > 0 && email.length > 0) {
				//alert("child1: " + child1);
				list.push({'name': name, 'email': email});
			}	

		}
	});
	
	//alert('list.size = ' + list.length);
	if (list.size > 0) {
		// add to contact list and clear out (only add once)
		var addToContacts = $.ajax({
			url: "/contacts",
			type: "POST",
			data: { list: list },
			dataType: "json"
		});
		addToContacts.done(function(data) {
			// remove the values from the table
			
		});
		
		addToContacts.fail(function(jqXHR, textStatus) {
			
		});
	}
});

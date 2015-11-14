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
	if (list.length > 0) {
		// add to contact list and clear out (only add once)
		
		//alert("add");
		
		//var json=[1,2,3,4];
	
		var addToContacts = $.ajax({
			url: "/contacts",
			type: "POST",
			data: {list:JSON.stringify(list)},
			dataType: "json"
		});
		addToContacts.done(function(data) {
			// add entries to contact list
			//document.reload();
			//return;
			//alert("data.html = " + data.html);
			rows.each(function(index) {
				if ($.trim($(this).text()).length > 0) {
					//alert($(this).text());
					var name = $.trim($(this).children(":first").text());
					var email = $.trim($(this).children(":nth-child(2)").text());
					if (name.length > 0 && email.length > 0) {
						//alert("child1: " + child1);
						$(this).children(":first").html("&nbsp;");
						$(this).children(":nth-child(2)").html("&nbsp;");
					}	

				}
			});
			
			
			$('#slider-contact-list').html(data.html);
			
		});
		
		addToContacts.fail(function(jqXHR, textStatus) {
			// not clear how to handle failures.
		});
	}
});

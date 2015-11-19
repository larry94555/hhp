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
					var td_name = $(this).children(":first");
					var td_email = $(this).children(":nth-child(2)");
					var name = $.trim(td_name.text());
					var email = $.trim(td_email.text());
					if (name.length > 0 && email.length > 0) {
						
//						var activeTd = $('#mainTable').find('td:focus');
//						
//						alert("activeTd: text = " + $(activeTd).text());
//						
//						if (activeTd == td_name || activeTd == td_email) {
//							var editor = $('#mainTable').parent().children('input');
//							editor.val('');
//							editor.blur();
//						}
												
						//alert("child1: " + child1);
						$(this).children(":first").html("&nbsp;");
						$(this).children(":nth-child(2)").html("&nbsp;");
					}	

				}
			});
			
			
			
			
			$('#slider-contact-list').html(data.html);
			
			$('#contact-continue').css('visibility','visible');
			
			
		});
		
		addToContacts.fail(function(jqXHR, textStatus) {
			// not clear how to handle failures.
		});
	}
});

$('body').on('click','span.remove-indicator',function(e) {
	// remove entry
	//alert("email: " + $(this).parent().parent().children('.contact-list-email').text());
	var removeContact = $.ajax({
		url: "/remove-contact",
		type: "POST",
		data: {email:$(this).parent().parent().children('.contact-list-email').text()},
		dataType: "json"
	});
	
	removeContact.done(function(data) {
		// add entries to contact list
		//document.reload();
		//return;
		//alert("data.html = " + data.html);
		
		$('#slider-contact-list').html(data.html);
	});
	
	removeContact.fail(function(jqXHR, textStatus) {
		// not clear how to handle failures.
	});
	
	e.preventDefault();
	return false;
});

$('body').on('click','a.contact-list-entry',function(e) {
	
	//$(this).parent().children(".contact-list-email").css("display","block");
	
	e.preventDefault();
	return false;
})

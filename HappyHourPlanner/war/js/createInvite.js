

$(function() {
	
	//To render the input device to multiple email input using BootStrap icon
	$('#invite-to-field').multiple_emails({position: "bottom"});
	
	// call ajax value to update the invitation details.
	// title, contact list, and invite text.
	var handleInviteChange = function() {
		
		if ($.trim($('#invite-to-field').val()).length <= 2) {
			// add disabled if not already there
			if (!$('#action-send-invite').hasClass("disabled")) {
				$('#action-send-invite').addClass("disabled");
				
			}
		}
		else {
			// remove disabled if there
			if ($('#action-send-invite').hasClass("disabled")) {
				$('#action-send-invite').removeClass("disabled");
			}
		}
		
		var updateInvite = $.ajax({
        	url: "/invite",
    		type: "POST",
    		data: { 
    			groupName: $('#invite-title').val(),
    			toList: $('#invite-to-field').val(),
    			text: $('#invite-draft-text').val(),
    			html: $('#invite-html').val(),
    			groupId: $('#invite-group-id').val(),
    			subject: $('#invite-subject').val(),
    			inviteInstanceId: $('#invite-instance-id').val(),
    			send: "update"
			},
    		dataType: "json"
        });
		
		updateInvite.done(function(data) {
			if (data.msg === "Need to login") {
				
				location.reload();
			}
		});
		
		updateInvite.fail(function(jqXHR,textStatus) {
		});
	};
	
	
	$('body').on('change','input.invite-setting', handleInviteChange);
	
	$('#invite-draft-text').bind('blur propertychange',handleInviteChange);
	
	$('#action-send-invite').click(function() {
		var sendInvite = $.ajax({
        	url: "/invite",
    		type: "POST",
    		data: { 
    			groupName: $('#invite-title').val(),
    			toList: $('#invite-to-field').val(),
    			text: $('#invite-draft-text').val(),
    			html: $('#invite-html').val(),
    			subject: $('#invite-subject').val(),
    			groupId: $('#invite-group-id').val(),
    			inviteInstanceId: $('#invite-instance-id').val(),
    			send: 'email'
			},
    		dataType: "json"
        });
		
		sendInvite.done(function(data) {
						
			if (data.msg === "Need to login") {

				location.reload();
			}
			else {
				// iterate through and set the email as sent (data.list)
				if (data.list) {
					data.list.forEach(function(item) {
						
						//alert("item = " + item);
						
						var id = "#id-"+item.toLowerCase().replace(/[@\.]/g,"_spcl_");
						if (!$(id).parent().hasClass("invite-sent")) {
							$(id).parent().addClass("invite-sent");
							
							//$(id).text($(id).text()+" (status: sent)");
							var statusMsgHtml = "<span class='invite-status-text'>sent</span>";
							
							$(statusMsgHtml).insertBefore(id);
							
							$(id).parent().find("a").css("display","none");
							
							
						}
						
						//alert("item = " + item);
						//$('#id-'+item.toLowerCase().replace(/[@\.]/g,"_spcl_")).parent().addClass('invite-sent');
						//alert("text = " + $('#id-'+item.toLowerCase().replace(/[@\.]/g, "_spcl_")).text())
						//alert("String = " + "#id-" + item.toLowerCase().replace(/@/g,"_at_"));
					});
				}
			}
		});
		
		sendInvite.fail(function(jqXHR,textStatus) {
		});
		
	});
	
});


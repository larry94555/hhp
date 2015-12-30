

$(function() {
	
	//To render the input device to multiple email input using BootStrap icon
	$('#invite-to-field').multiple_emails({position: "bottom"});
	
	// call ajax value to update the invitation details.
	// title, contact list, and invite text.
	var handleInviteChange = function() {
		var updateInvite = $.ajax({
        	url: "/invite",
    		type: "POST",
    		data: { 
    			groupName: $('#invite-title').val(),
    			toList: JSON.stringify($('#invite-to-field').val()),
    			text: $('#invite-draft-text').val(),
    			groupId: $('#invite-group-id').val()
			},
    		dataType: "json"
        });
		
		updateInvite.done(function(data) {
		});
		
		updateInvite.fail(function(jqXHR,textStatus) {
		});
	};
	
	
	$('body').on('change','input.invite-setting', handleInviteChange);
	
	$('#invite-draft-text').bind('blur propertychange',handleInviteChange);
	
});


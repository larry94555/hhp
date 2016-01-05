

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
		});
		
		sendInvite.fail(function(jqXHR,textStatus) {
		});
		
	});
	
});


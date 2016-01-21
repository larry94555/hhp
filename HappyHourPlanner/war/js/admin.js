function deselect(e) {
  $('.pop').slideFadeToggle(function() {
    e.removeClass('selected');
  });    
}

$(function() {
  $('#contact').on('click', function() {
    if($(this).hasClass('selected')) {
      deselect($(this));               
    } else {
      $(this).addClass('selected');
      $('.pop').slideFadeToggle();
    }
    return false;
  });

  $('.close').on('click', function() {
    deselect($('#contact'));
    return false;
  });
});

$.fn.slideFadeToggle = function(easing, callback) {
  return this.animate({ opacity: 'toggle', height: 'toggle' }, 'fast', easing, callback);
};

$(document).ready(function() {
	$('#table_id').DataTable();
	
	$('#table_id').on( 'click', 'tbody tr', function () {
    	//alert($(this).children('.row_email').text());
		
	    // Getting the link 
	    var editTableBox = '#edit-table-box';
	    // Fade in the popup
	    $(editTableBox).fadeIn(300);
	    
	    // Set the center alignment padding + border see css style
	    var popMargTop = ($(editTableBox).height() + 24) / 2;
	    var popMargLeft = ($(editTableBox).width() + 24) / 2;
	    
	    $(editTableBox).css({
	    	'margin-top' : -popMargTop,
	    	'margin-left' : -popMargLeft
	    });
	    
	    // set the current value
	    $('#edit-table-message').hide();
	    $('#edit-table-username').text($(this).children('.row_email').text());
	    $('#edit-table-verified').val($(this).children('.row_verified').text());
	    $('#edit-table-disabled').val($(this).children('.row_disabled').text());
	    $('#edit-table-change-password').val($(this).children('.row_change_password').text());
	    $('#edit-table-first-time').val($(this).children('.row_first_time_user').text());
	    $("#edit-table-current-state").val($(this).children('.row_current_state').attr("data-value"));
	    $("#edit-table-email-text-only").val($(this).children('.row_email_text_only').text());
	    $('#edit-table-delete').val('no');
	    
	    //alert("test: value = " + $(this).children('.row_current_state').attr("data-value"));
	    
	    // Add the mask to body
	    $('body').append('<div id="mask"></div>');
	    $('#mask').fadeIn(300);
	    
	    return false;
 
	} );
	

	$('#the-button-text').click(function() {
		
		if ($('#edit-table-delete').val() === "yes") {
			if (confirm("Delete user: " + $('#edit-table-username').text() + "?") === false) {
				return false;
			}
		}
		
		// send ajax message
		var updateUser = $.ajax({
			url: "updateuser",
			type: "POST",
			data: { 
				username: $('#edit-table-username').text(), 
				verified: $('#edit-table-verified').val(),
				disabled:$('#edit-table-disabled').val(),
				changePassword:$('#edit-table-change-password').val(),
				firstTimeUser:$('#edit-table-first-time').val(),
				currentState:$('#edit-table-current-state').val(),
				emailTextOnly:$('#edit-table-email-text-only').val(),
				delete: $('#edit-table-delete').val()
			},
			dataType: "text"
		});
		
		updateUser.done(function(msg) {
			
			// refresh page
			location.reload();
		});

		updateUser.fail(function(jqXHR, textStatus) {
		
			// display error message
			$('#edit-table-message').text("System is down...please try again later.");
			$('#edit-table-message').show();
			//alert( "Request failed: " + textStatus );
		});
		
		return false;
		
	});
	

	// When clicking on the button close or the mask layer the popup closed
	$('body').on('click', 'a.close-popup',function() { 
	  $('#mask, .edit-table-popup').fadeOut(300, function() {
	    $('#mask').remove();  
	  });
	  return false;    
	}); 
	
	
	
} );
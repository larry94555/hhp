function deselect(e) {
  $('.pop').slideFadeToggle(function() {
    e.removeClass('selected');
  });    
}

$(function() {
	  $('body').on('click','a#contact', function() {
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
	  
	  var alreadySent = false;
	  $('body').on('click','a#resendverify',function() {
		    
		    if (alreadySent) {
		    	return false;
		    }
		    alreadySent = true;
		  
		    // do ajax call to resend
			var resendVerifyEmail = $.ajax({
				url: "/resendVerify",
				type: "POST",
				data: { username: $.trim($('#hello-user').text()) },
				dataType: "json"
			});
			
			resendVerifyEmail.done(function(data) {
				
					//location.reload();
					alreadySent=false;
				
	
			});
	
		resendVerifyEmail.fail(function(jqXHR, textStatus) {
	
			alreadySent=false;
			//alert( "Request failed: " + textStatus );
		});
		  
		  
		  return false;
	  });
});

$.fn.slideFadeToggle = function(easing, callback) {
  return this.animate({ opacity: 'toggle', height: 'toggle' }, 'fast', easing, callback);
};


function deselect(e) {
  $('.pop').slideFadeToggle(function() {
    e.removeClass('selected');
  });    
}

$(function() {
	  $('body').on('click','a#contact', function() {
	    if($(this).hasClass('selected')) {
	      deselect($(this));
	      $('#contact').text("Contact Us")
	    } else {
	      $(this).addClass('selected');
	      $('.pop').slideFadeToggle();
	      $('#contact').text("Cancel");
	    }
	    return false;
	  });
	  
	  $('body').on('click','input#comment_submit', function() {
		 //alert("click");
		 return false;
	  });
	  
	  $('body').on('click','a.stepOne-continue', function() {
		  
		  $('#wizard-t-1').click();
		  
	  });
	  
	  $('body').on('click','a#stepTwo-continue', function() {
		  $('#wizard-t-2').click();
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
			
					alreadySent=false;
					
					location.reload();
				
	
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



function validateEmail(sEmail) {
	
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    if (filter.test(sEmail)) {

        return true;

    }

    else {

        return false;
    }
}

function createCookie(name, value, days) {
    var expires;

    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    } else {
        expires = "";
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function eraseCookie(name) {
    createCookie(name, "", -1);
}

$(document).ready(function() {
	
	$('a.sign-out-button').click(function() {
		
		var signout = $.ajax({
			url: "/signout",
			type: "POST",
			data: { user: $.trim($('#hello-user').text()) },
			dataType: "text"
		});
		
		
		signout.done(function(msg) {
			location.reload();
		});	
		
		signout.fail(function(msg) {
			location.reload();
		});
			
		
	});
		
	
	$('a.login-window').click(function() {
	    
	    //Getting the variable's value from a link 
	    var loginBox = $(this).attr('href');

	    //Fade in the Popup
	    $(loginBox).fadeIn(300);
	    
	    //Set the center alignment padding + border see css style
	    var popMargTop = ($(loginBox).height() + 24) / 2; 
	    var popMargLeft = ($(loginBox).width() + 24) / 2; 
	    
	    $(loginBox).css({ 
	        'margin-top' : -popMargTop,
	        'margin-left' : -popMargLeft
	    });
	    
	    $('#the-button-text').text('log in');
	    $('#login-message').show();
	    $('#password2-label').hide();
	    
	    // Add the mask to body
	    $('body').append('<div id="mask"></div>');
	    $('#mask').fadeIn(300);
	    
	    return false;
	});
	
	$('a.signup-window').click(function() {
	    
	    //Getting the variable's value from a link 
	    var loginBox = $(this).attr('href');

	    //Fade in the Popup
	    $(loginBox).fadeIn(300);
	    
	    //Set the center alignment padding + border see css style
	    var popMargTop = ($(loginBox).height() + 24) / 2; 
	    var popMargLeft = ($(loginBox).width() + 24) / 2; 
	    
	    $(loginBox).css({ 
	        'margin-top' : -popMargTop,
	        'margin-left' : -popMargLeft
	    });
	    
	    $('#the-button-text').text('sign up');
	    $('#login-message').hide();
	    $('#password2-label').show();
	    
	    // Add the mask to body
	    $('body').append('<div id="mask"></div>');
	    $('#mask').fadeIn(300);
	    
	    return false;
	});
	
	var alreadyClicked=false;
	
	var handleClick = function() {
		
		if (alreadyClicked) {
			return false;
		}
		
		if ($.trim($('#username').val()) === '') {
			$('#login-message').hide();
			$('#login-message').text("Email address required.");
			$('#login-message').show();
			return false;
		}
		
		if (validateEmail($.trim($('#username').val())) === false) {
			$('#login-message').hide();
			$('#login-message').text("Email address is not valid.");
			$('#login-message').show();
			return false;
		}
		
		if ($.trim($('#password').val()) == '') {
			$('#login-message').hide();
			$('#login-message').text("Password required.");
			$('#login-message').show();
			return false;
		}
		
		if ($('#the-button-text').text() === 'sign up') {
			
			if ($.trim($('#password2').val()) == '') {
				$('#login-message').hide();
				$('#login-message').text("Password re-enter required.");
				$('#login-message').show();
				return false;
			}
			
			if ($.trim($('#password').val()) !== $.trim($('#password2').val())) {
				$('#login-message').hide();
				$('#login-message').text("Passwords don't match.");
				$('#login-message').show();
				return false;
			}
			
			alreadyClicked=true;
			
			$('#login-message').hide();
			
			var signUp = $.ajax({
				url: "/signup",
				type: "POST",
				data: { username: $('#username').val(), pwd: $('#password').val(),
					pwd2:$('#password2').val() },
				dataType: "json"
			});
			
			signUp.done(function(data) {
				
				//alert("data = " + data);
				//alert("data.msg = " + data.msg);
				//alert("data.sessionId = " + data.sessionId);
				//alert("data.cookieName = " + data.cookieName);
				//$('#password').val('');	
				if ($.trim(data.msg) === "signup" ||
						$.trim(data.msg) === "login") {
					
					// save cookie
					if (readCookie(data.cookieName)==null) {
						//alert("Adding cookie");
						createCookie(data.cookieName,data.sessionId,365);
					}
					else {
						//alert("Cookie already exists");
					}
					
					location.reload();
					alreadyClicked=false;
				}
				else {
					
					$('#login-message').hide();
					$('#login-message').text(data.msg);
					$('#login-message').show();
					
					alreadyClicked=false;
	
				}
	
			});
	
		signUp.fail(function(jqXHR, textStatus) {
			$('login-message').text("System is down...please try again later.");
			alreadyClicked=false;
			//alert( "Request failed: " + textStatus );
		});
			
		}
		else if ($('#the-button-text').text() === 'log in') {
			
			alreadyClicked=true;
			
			$('#login-message').hide();
			
			var login = $.ajax({
				url: "/login",
				type: "POST",
				data: { user: $('#username').val(), pwd: $('#password').val() },
				dataType: "json"
			});
			
			
			login.done(function(data) {
				//alert("text = " + msg);
				//$('#password').val('');
				if ($.trim(data.msg) === "login" ) {
					
					// extract cookie 
					
					
					// go to the member page
					location.reload();
					
					//$('#login-message').hide();
					//$('#the-button-text').text('Create Account');
				
					alreadyClicked=false;
				
				}	
				else {
					
					$('#login-message').hide();
					$('#login-message').text(data.msg);
					$('#login-message').show();
					
					alreadyClicked=false;
	
				}
	
			});
	
			login.fail(function(jqXHR, textStatus) {
				$('login-message').text("System is down...please try again later.");
				//alert( "Request failed: " + textStatus );
				
				alreadyClicked=false;
			});
			
			return false;
	
			
		}
		
		return false;		
	};
	
	// support return key for password
	$('#password').change(function() {
		if ($.trim($('#username').val()) === '') {
			return false;
		}
		if ($.trim($('#password').val()) === '') {
			return false;
		}
		
		if ($('#the-button-text').text() !== 'log in') {
			return false;
		}
		
		handleClick();
		
	});
	
	// support return key for password2
	$('#password2').change(function() {
		
		if ($.trim($('#username').val()) === '') {
			return false;
		}
		if ($.trim($('#password').val()) === '') {
			return false;
		}
		if ($.trim($('#password2').val()) === '') {
			return false;
		}
		if ($('#password').val() !== $('#password2').val()) {
			return false;
		}
		
		if ($('#the-button-text').text() !== 'sign up') {
			return false;
		}
		
		handleClick();
		
	});

	$('#the-button-text').click(handleClick);
	
$('form.login-window').click(function() {
    
    //Getting the variable's value from a link 
    var loginBox = $(this).attr('action');

    //Fade in the Popup
    $(loginBox).fadeIn(300);
    
    //Set the center alignment padding + border see css style
    var popMargTop = ($(loginBox).height() + 24) / 2; 
    var popMargLeft = ($(loginBox).width() + 24) / 2; 
    
    $(loginBox).css({ 
        'margin-top' : -popMargTop,
        'margin-left' : -popMargLeft
    });
    
    $('#the-button-text').text('Sign up');
    $('#login-message').hide();
    
    // Add the mask to body
    $('body').append('<div id="mask"></div>');
    $('#mask').fadeIn(300);
    
    return false;
});



// When clicking on the button close or the mask layer the popup closed
$('body').on('click', 'a.close',function() { 
  $('#mask, .login-popup').fadeOut(300, function() {
    $('#mask').remove();  
});
return false;    
}); 
});

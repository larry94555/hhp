<!DOCTYPE html>
<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.model.Contact" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="com.happyhourplanner.common.Constant" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
	final User user = Util.checkForUser(request,response);
	if (user == null) {
		// in this case, do a sign out.
		request.getSession().invalidate();
	    Util.removeSessionCookie(response);
	    pageContext.forward("/");
	}
	
	final String status = (String)session.getAttribute(Constant.STATUS);
	
	final int currentState =  user.getCurrentState();
	
%>


<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Happy Hour Planner</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <!--[if lte IE 8]><script src="/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="/css/standard.css" />
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/querystep/css/main.css">
        <link rel="stylesheet" href="/css/login.css" />
        <link rel="stylesheet" href="/querystep/css/jquery.steps.css">
        <link rel="stylesheet" href="/css/header.css" />
        <link rel="stylesheet" type="text/css" href="/css/emailpopup.css">
        <link rel="stylesheet" href="/css/slidernav.css" />
        <link href="/css/prettify.css" rel="stylesheet">
        <link href="/css/bootstrap.min.css" rel="stylesheet">
    	<!-- <link href="/css/bootstrap-combined.no-icons.min.css" rel="stylesheet"> -->
    	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="/css/font-awesome.css" rel="stylesheet">
		<link rel="stylesheet" href="/css/preferences.css" />
        <script src="/querystep/lib/modernizr-2.6.2.min.js"></script>
        <script src="/querystep/lib/jquery-1.9.1.min.js"></script>
        <script src="/querystep/lib/jquery.cookie-1.3.1.js"></script>
        <script src="/querystep/lib/jquery.steps.js"></script>
        <script src="/js/slidernav.js"></script>
        <script type="text/javascript" language="javascript" src="/js/emailpopup.js"></script>     
    	<script src="/js/bootstrap.min.js"></script>
    	<script src="/js/prettify.js"></script>
		<link href="/css/edittableindex.css" rel="stylesheet">
		<script src="/js/contact-input.js"></script>
    	<script src="/js/mindmup-rewrite-editabletable.js"></script>
    	<link href="/css/contact-list.css" rel="stylesheet">	
    	<link rel="stylesheet" href="/css/searchableOptionList.css">
 		<link rel="stylesheet" href="/css/sol.css">
    	<script type="text/javascript" src="/js/sol.js"></script>
    </head>
    <body class="landing">
        
        <jsp:include page="header.jsp" />
        
		<section id="banner">
			<h2><strong>Happy Hour Planner</strong></h2>
			<ul class="actions">
			</ul>
		</section>
		
		 <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->
	
        <div class="content">
            <h1>&nbsp;</h1>

            <div id="wizard">
                <h2>Verify Email Account</h2>
                <section>
                
                <% if (user.isVerified()) { %>
                
                	<p>Your account has already been activated.</p>
                	<p>Click <a id="stepOne-continue" href="#">here</a> to continue.</p>
                	
                <% } else if (status == null) { %>	
              
                	
                	<% if (Util.checkDateInMinutes((java.util.Date)session.getAttribute("verify_resend"),30)) { %>
                
                	<p>We&apos;ve resent the email containing a link that will verify your email account.</p>
                	<p>Please check your spam folder if the email doesn&apos;t appear within a few minutes.</p>
                
                	<% } else { %>
                	
                	<p>We&apos;ve sent you an email containing a link that will allow you to verify your email account.</p>
                	<p>Please check your spam folder if the email doesn&apos;t appear within a few minutes.</p>
                	<p>Click <a href="#" id="resendverify">here</a> to resend the email.</p>
                	
                	<% } %>
                
                <% } else if (!status.equals(Constant.BAD_ACTIVATION_CODE)) { %>
                
                	<p>Thanks for verifying your email.  Welcome to Happy Hour Planner!</p>
                	<p>Click <a href=#">here</a> to continue.</p>
                
                <% } else { %>
                
                	<p>The activation code you selected was old. A more recent one was sent.</p>
                	<p>Click <a href="#" id="resendverify">here</a> to resend the email.</p>
                
                	<p>Thanks for verifying your email.  Welcome to Happy Hour Planner!</p>
                	<p>Click <a href=#">here</a> to continue.</p>
                
                
                <% 
                
                	session.removeAttribute(Constant.STATUS);
                
                	} 
                	
                %>
                
                
                    <div class="messagepop pop">
  						<form method="post" id="new_message" action="/messages">
    						<p><label for="email">Your email address</label><input type="text" name="email-contact" id="email-contact" /></p>
    						<p><label for="body">Message</label><textarea rows="6" name="contact-body" id="contact-body"></textarea></p>
    						<p><input type="submit" value="Send Message" name="comment_submit" id="comment_submit"/></p>
  						</form>
					</div>
					
					<a href="#" id="contact" class="special button">Contact Us</a>
                    
                </section>

                <h2>Create Contact List</h2>
                <section>
                
                	<% if (status != null && status.equals(Constant.JUST_ACTIVATED)) { %>
                
                    <p>Thanks for confirming your email address!</p>
                   
                    
                    <% } %>
                    
                    <p>The next step is to add your contacts to the table below and click 'add'.</p>
                    <p>You need at least 1 contact.<span id='contact-continue'> When done, click <a id='stepTwo-continue' href='#'>here</a> to continue.</span></p>
                    
                    <div class="add-contact-list-area">
                    
                    	<button id='add-contact-button' class='slider-button' title='add a contact.'>add</button>
                    	<button id='email-contact-button' class='slider-button' title='forward an email'>email the contact</button>
                    	<button id='edit-contact-button' class='slider-button' title='edit existing list'>edit list</button> 
                    	<span id='edit-contact-recycle-bin' class='glyphicon glyphicon-trash'>2</span>
					  	<table id="mainTable" class="table table-striped">
            				<thead><tr><th>Name</th><th>Email Address</th></tr></thead>
            				<tbody>
            					<% for (int i=0; i < 10; i++) { %>
            					
              					<tr><td class='contact-name'>&nbsp;</td><td class='contact-email'>&nbsp;</td></tr>
              					
              					<% } %>
            				</tbody>
          				</table>
					</div>
                    <div id="slider">
					  <div class="slider-content">
					  	<ul id='slider-contact-list'>
					  
					  <%= UserAccountHandler.generateContactListHtml(user) %>   
					    
					  	
					  	</ul>
					  </div>
					 
					</div>
                    
                </section>

                <h2>Set Your Preferences</h2>
                <section>
 <div id="pref-container">
  <div id="pref-form1">
  
<div id='select-container'>

<a href="#" id="accept-preferences" class="special button">Skip</a>

<div id="preferences-title">What are your preferences?</div>

<div id="preference-settings">
<input type="checkbox" name="pref-allow-suggestions" class="standard-checkbox" id="pref-allow-suggestions" checked="checked" /><label for="pref-allow-suggestions">Let People Suggest Places</label><br /><br />

<input type="checkbox" name="skip-low-ratings" class="standard-checkbox place-search-option" id="skip-low-ratings" checked="checked" />
<label for="skip-low-ratings">Disallow places rated lower than</label><input type="text" name="min-rating" id="min-rating" class="place-search-option" value="2"></input> stars<br/><br/>

<input type="checkbox" name="pref-restaurants-only" class="standard-checkbox place-search-option" id="pref-restaurants-only" /><label for="pref-restaurants-only">Restaurants only</label><br /><br />

<input type="checkbox" name="pref-spirits-too" class="standard-checkbox place-search-option" id="pref-spirits-too" /><label for="pref-spirits-too">Full bar (wine, beer, and spirits)</label><br /><br />

<input type="checkbox" name="pref-allow-m" class="standard-checkbox" id="pref-allow-m" checked="checked" /><label for="pref-allow-m">M</label>
<input type="checkbox" name="pref-allow-t" class="standard-checkbox" id="pref-allow-t" checked="checked" /><label for="pref-allow-t">T</label>
<input type="checkbox" name="pref-allow-w" class="standard-checkbox" id="pref-allow-w" checked="checked" /><label for="pref-allow-w">W</label>
<input type="checkbox" name="pref-allow-th" class="standard-checkbox" id="pref-allow-th" checked="checked" /><label for="pref-allow-th">Th</label>
<input type="checkbox" name="pref-allow-f" class="standard-checkbox" id="pref-allow-f" checked="checked" /><label for="pref-allow-f">F</label>
<input type="checkbox" name="pref-allow-sa" class="standard-checkbox" id="pref-allow-sa" /><label for="pref-allow-sa">Sat</label>
<input type="checkbox" name="pref-allow-su" class="standard-checkbox" id="pref-allow-su"  /><label for="pref-allow-su">Sun</label>



</div>

<label for="default-location">Location:</label><span id="def-location">current (<span id="detected-location"></span>)</span><a id="set-default-location">set</a>
<input type="hidden" id="detected-latitude"></input>
<input type="hidden" id="detected-longitude"></input>
<input type="hidden" id="place-search-offset" value="0"></input>

<div id="override-current-location">
<input type="text" name="pref-near" id="pref-near" class="place-search-option" placeholder="Enter city, neighborhood, zip, or cross streets" />
</div>

<div id='container'> 
<label for="pref-list">Places:</label><br /> 

<select id="my-select" name="character" multiple="multiple"> 
<option disabled="disabled">Loading...</option>
</select>
</div>

<a href="http://yelp.com" title="list powered by yelp"><img id='yelp-image' src="/images/yelp_powered_btn_dark.png"></a>

<div id='select-container2'>

<br/><label for="pref-list">Disallow:</label><br />

<!--<select id="my-select2" name="character" multiple="multiple">
<option disabled="disabled">Loading...</option>
</select> -->

</div>
</div>
    
    </div>
</div>
                </section>

                <h2>Send Out Invite</h2>
                <section>
                	<p>Here is the invite -- make any changes you like:</p>
                	<textarea class="yellow-page"></textarea>
                    <p>Who do you want to invite:
                    	<select>
                    		<option>-Select-</option>
                    		<option>Entire Contact List</option>
                    		<option>Group List</option>
                    	</select>
                    </p>
                    <p>Which Group List:
                    	<select>
                    		<option>-Select-</option>
                    		<option>Group A</option>
                    		<option>Group B</option>
                    		<option>Create New Group</option>
                    	</select>
                    </p>
                    
                </section>
                <h2>Check Out Status</h2>
                <section>
                    <p>Date Range:
                    	<select>
                    		<option>This week</option>
                    		<option>Next 2 weeks</option>
                    		<option>Next 3 weeks</option>
                    		<option>Next 4 weeks</option>
                    		<option>Next 5 weeks</option>
                    		<option>Next 6 weeks</option>
                    	</select>
                    </p>
                    <p>Day of Week:
                    	<select>
                    		<option>Any day</option>
                    		<option>Weekend</option>
                    		<option>Week Day</option>
                    	</select>
                    </p>
                    <p>Time of Day:
                    	<select>
                    		<option>any time</option>
                    		<option>3pm</option>
                    		<option>4pm</option>
                    		<option>5pm</option>
                    		<option>6pm</option>
                    		<option>7pm</option>
                    		<option>8pm</option>
                    	</select>
                    </p>
                    <p>Location:
                    	<select>
                    		<option>San Jose</option>
                    		<option>Sunnyvale</option>
                    		<option>Santa Cruz</option>
                    	</select>
                    </p>
                    <p>Places:
                    	<select>
                    		<option>The Brit</option>
                    		<option>The Wagon Wheel</option>
                    		<option>Somewhere new</option>
                    		<option>Don't care</option>
                    	</select>
                    </p>
                </section>
                <h2>Save the Date</h2>
                <section>
                    <p>Voting has ended.  Here are the details that accommodate the most people:</p>
                    <p>Do you wish to proceed or cancel?</p>
                </section>
            </div>
        </div>
        
        <!-- Scripts -->
		<script src="/js/skel.min.js"></script>
		<script src="/js/util.js"></script>
		<!--[if lte IE 8]><script src="/js/ie/respond.min.js"></script><![endif]-->
		<script src="/js/main.js"></script>
		<script src="/js/login.js"></script>
		<script src="/js/wizard.js"></script>
		<script>
			$(function () {
			    $("#wizard").steps({
			        headerTag: "h2",
			        bodyTag: "section",
			        enableKeyNavigation: false,
			        startIndex: <%= currentState %>,
			        enablePagination: false,
			        transitionEffect: "slideLeft",
			        stepsOrientation: "vertical"
			    });
			    $('#slider').sliderNav({height:'500'});
			    $('#mainTable').editableTableWidget().contactInput().find('td:first').focus();
  				$('#mainTable td').on('mouseover', function() {
					if ($.trim($(this).text()).length===0 && $(this).prop('tabindex')===0) $(this).focus();
				});
			
  				window.prettyPrint && prettyPrint();
  				if (<%= currentState %> > 1) {
  					$('#contact-continue').css('visibility','visible');
  				}
			});
			
			$(function() {
        		// initialize sol
        		//$('#my-select').searchableOptionList({});
        		//$('#my-select2').searchableOptionList({});
    		});
   			
		</script>
        
    </body>
</html>
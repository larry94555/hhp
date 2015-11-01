<!DOCTYPE html>
<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
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
//	else if (!user.isFirstTime()) {
//		// forward to wizard.jsp
//		pageContext.forward("/member/member.jsp");
//	}
	
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
        <script src="/querystep/lib/modernizr-2.6.2.min.js"></script>
        <script src="/querystep/lib/jquery-1.9.1.min.js"></script>
        <script src="/querystep/lib/jquery.cookie-1.3.1.js"></script>
        <script src="/querystep/lib/jquery.steps.js"></script>
        <script src="/js/slidernav.js"></script>
        <script type="text/javascript" language="javascript" src="/js/emailpopup.js"></script>		
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
    						<p><label for="email">Your email address</label><input type="text" size="30" name="email" id="email" /></p>
    						<p><label for="body">Message</label><textarea rows="6" name="body" id="body" cols="35"></textarea></p>
    						<p><input type="submit" value="Send Message" name="commit" id="message_submit"/> or <a class="close" href="/">Cancel</a></p>
  						</form>
					</div>
					
					<a href="#" id="contact">Contact Us</a>
                    
                </section>

                <h2>Create Contact List</h2>
                <section>
                
                	<% if (status != null && status.equals(Constant.JUST_ACTIVATED)) { %>
                
                    <p>Thanks for confirming your email address!</p>
                   
                    
                    <% } %>
                    
                    <p>The next step is to add your contacts to the yellow page and click 'add'.</p>
                    <p>When you have at least 1 contacts, click <a href='#'>here</a> to continue.</p>
                    
                    <div class="add-contact-list-area">
                    	<button class='slider-button' title='add a contact.'>add</button>
                    	<button class='slider-button' title='forward an email'>email the contact</button>
                    	<button class='slider-button' title='edit existing list'>edit list</button> 
					  	<textarea id="add-contacts" class="yellow-page" placeholder="George Washington George@FirstPres.gov"></textarea>
					</div>
                    <div id="slider">
					  <div class="slider-content">
					    <ul>
					      <li id="a"><a name="a" class="title">A</a>
					        <ul>
					          <li><a href="/">Adam</a></li>
							  <li><a href="/">Alex</a></li>
							  <li><a href="/">Ali</a></li>
							  <li><a href="/">Apple</a></li>
							  <li><a href="/">Arthur</a></li>
							  <li><a href="/">Ashley</a></li>
					        </ul>
					      </li>
					      <li id="b"><a name="b" class="title">B</a>
					        <ul>
					          <li><a href="/">Barry</a></li>
							  <li><a href="/">Becky</a></li>
							  <li><a href="/">Biff</a></li>
							  <li><a href="/">Billy</a></li>
							  <li><a href="/">Bozarking</a></li>
							  <li><a href="/">Bryan</a></li>
					        </ul>
					      </li>
						  <li id="c"><a name="c" class="title">c</a>
						         <ul>
									<li><a href="/">Calista</a></li>
									<li><a href="/">Cathy</a></li>
									<li><a href="/">Chris</a></li>
									<li><a href="/">Cinderella</a></li>
									<li><a href="/">Corky</a></li>
									<li><a href="/">Cypher</a></li>
								</ul>
							</li>
							<li id="d"><a name="d" class="title">d</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="e"><a name="e" class="title">E</a>
								<ul>
									<li><a href="/">Barry</a></li>
									<li><a href="/">Becky</a></li>
									<li><a href="/">Biff</a></li>
									<li><a href="/">Billy</a></li>
									<li><a href="/">Bozarking</a></li>
									<li><a href="/">Bryan</a></li>
								</ul>
							</li>
							<li id="f"><a name="f" class="title">f</a>
								<ul>
									<li><a href="/">Calista</a></li>
									<li><a href="/">Cathy</a></li>
									<li><a href="/">Chris</a></li>
									<li><a href="/">Cinderella</a></li>
									<li><a href="/">Corky</a></li>
									<li><a href="/">Cypher</a></li>
								</ul>
							</li>
							<li id="g"><a name="g" class="title">g</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="h"><a name="h" class="title">h</a>
								<ul>
									<li><a href="/">Barry</a></li>
									<li><a href="/">Becky</a></li>
									<li><a href="/">Biff</a></li>
									<li><a href="/">Billy</a></li>
									<li><a href="/">Bozarking</a></li>
									<li><a href="/">Bryan</a></li>
								</ul>
							</li>
							<li id="i"><a name="i" class="title">i</a>
								<ul>
									<li><a href="/">Calista</a></li>
									<li><a href="/">Cathy</a></li>
									<li><a href="/">Chris</a></li>
									<li><a href="/">Cinderella</a></li>
									<li><a href="/">Corky</a></li>
									<li><a href="/">Cypher</a></li>
								</ul>
							</li>
							<li id="j"><a name="j" class="title">j</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="k"><a name="k" class="title">k</a>
								<ul>
									<li><a href="/">Barry</a></li>
									<li><a href="/">Becky</a></li>
									<li><a href="/">Biff</a></li>
									<li><a href="/">Billy</a></li>
									<li><a href="/">Bozarking</a></li>
									<li><a href="/">Bryan</a></li>
								</ul>
							</li>
							<li id="l"><a name="l" class="title">l</a>
								<ul>
									<li><a href="/">Calista</a></li>
									<li><a href="/">Cathy</a></li>
									<li><a href="/">Chris</a></li>
									<li><a href="/">Cinderella</a></li>
									<li><a href="/">Corky</a></li>
									<li><a href="/">Cypher</a></li>
								</ul>
							</li>
							<li id="m"><a name="m" class="title">m</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="n"><a name="n" class="title">n</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="o"><a name="o" class="title">o</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="p"><a name="p" class="title">p</a>
								<ul>
									<li><a href="/">Barry</a></li>
									<li><a href="/">Becky</a></li>
									<li><a href="/">Biff</a></li>
									<li><a href="/">Billy</a></li>
									<li><a href="/">Bozarking</a></li>
									<li><a href="/">Bryan</a></li>
								</ul>
							</li>
							<li id="q"><a name="q" class="title">q</a>
								<ul>
									<li><a href="/">Calista</a></li>
									<li><a href="/">Cathy</a></li>
									<li><a href="/">Chris</a></li>
									<li><a href="/">Cinderella</a></li>
									<li><a href="/">Corky</a></li>
									<li><a href="/">Cypher</a></li>
								</ul>
							</li>
							<li id="r"><a name="r" class="title">r</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="s"><a name="s" class="title">s</a>
								<ul>
									<li><a href="/">Barry</a></li>
									<li><a href="/">Becky</a></li>
									<li><a href="/">Biff</a></li>
									<li><a href="/">Billy</a></li>
									<li><a href="/">Bozarking</a></li>
									<li><a href="/">Bryan</a></li>
								</ul>
							</li>
							<li id="t"><a name="t" class="title">t</a>
								<ul>
									<li><a href="/">Calista</a></li>
									<li><a href="/">Cathy</a></li>
									<li><a href="/">Chris</a></li>
									<li><a href="/">Cinderella</a></li>
									<li><a href="/">Corky</a></li>
									<li><a href="/">Cypher</a></li>
								</ul>
							</li>
							<li id="u"><a name="u" class="title">u</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="v"><a name="v" class="title">v</a>
								<ul>
									<li><a href="/">Barry</a></li>
									<li><a href="/">Becky</a></li>
									<li><a href="/">Biff</a></li>
									<li><a href="/">Billy</a></li>
									<li><a href="/">Bozarking</a></li>
									<li><a href="/">Bryan</a></li>
								</ul>
							</li>
							<li id="w"><a name="w" class="title">w</a>
								<ul>
									<li><a href="/">Calista</a></li>
									<li><a href="/">Cathy</a></li>
									<li><a href="/">Chris</a></li>
									<li><a href="/">Cinderella</a></li>
									<li><a href="/">Corky</a></li>
									<li><a href="/">Cypher</a></li>
								</ul>
							</li>
							<li id="x"><a name="x" class="title">x</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="y"><a name="y" class="title">y</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
							<li id="z"><a name="z" class="title">z</a>
								<ul>
									<li><a href="/">damien</a></li>
									<li><a href="/">danny</a></li>
									<li><a href="/">denver</a></li>
									<li><a href="/">devon</a></li>
									<li><a href="/">doug</a></li>
									<li><a href="/">dustin</a></li>
								</ul>
							</li>
					    </ul>
					  </div>
					 
					</div>
                    
                </section>

                <h2>Set Your Availability and Preferences</h2>
                <section>
                	<p>Please answer a few questions:<p>
                    <p>Location?
                    	<select>
                    		<option>-Select-</option>
                    		<option>Specify a Place</option>
                    		<option>Let's Vote</option>
                    		<option>Specify a Zip Code</option>
                    	</select>
                    </p>
                    <p>When?
                    	<select>
                    		<option>-Select-</option>
                    		<option>Let's Vote</option>
                    		<option>Any Evening</option>
                    		<option>Specify a Time</option>
                    		<option>Specific Day of Week</option>
                    		<option>Specific Date</option>
                    	</select>
                    </p>
                    <p>Place?
                    	<select>
                    		<option>-Select-</option>
                    		<option>Let's Vote</option>
                    		<option>Recommended</option>
                    		<option>List of Places</option>
                    		<option>Specific Place</option>
                    	</select>
                    </p>
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
			});
		</script>
        
    </body>
</html>
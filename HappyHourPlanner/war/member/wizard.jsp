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
	else if (!user.isFirstTime()) {
		// forward to wizard.jsp
		pageContext.forward("/member/member.jsp");
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
        <!--[if lte IE 8]><script src="../js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="../css/standard.css" />
        <link rel="stylesheet" href="../css/normalize.css">
        <link rel="stylesheet" href="../querystep/css/main.css">
        <link rel="stylesheet" href="../querystep/css/jquery.steps.css">
        <link rel="stylesheet" href="../css/header.css" />
        <link rel="stylesheet" type="text/css" href="../css/emailpopup.css">
        <script src="../querystep/lib/modernizr-2.6.2.min.js"></script>
        <script src="../querystep/lib/jquery-1.9.1.min.js"></script>
        <script src="../querystep/lib/jquery.cookie-1.3.1.js"></script>
        <script src="../querystep/lib/jquery.steps.js"></script>
        <script type="text/javascript" language="javascript" src="../js/emailpopup.js"></script>		
    </head>
    <body class="landing">
        
        <jsp:include page="header.jsp" flush="true" />
        
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

            <script>
                $(function ()
                {
                    $("#wizard").steps({
                        headerTag: "h2",
                        bodyTag: "section",
                        enableKeyNavigation: false,
                        startIndex: <%= currentState %>,
                        enablePagination: false,
                        transitionEffect: "slideLeft",
                        stepsOrientation: "vertical"
                    });
                });
            </script>

            <div id="wizard">
                <h2>Verify Email Account</h2>
                <section>
                
                <% if (user.isVerified()) { %>
                
                	<p>Your account has already been activated.</p>
                	<p>Click <a id="stepOne-continue" href="#">here</a> to continue.</p>
                	
                <% } else if (status == null || !status.equals(Constant.BAD_ACTIVATION_CODE)) { %>	
                
                	<p>We&apos;ve sent you an email containing a link that will allow you to verify your email account.</p>
                	<p>Please check your spam folder if the email doesn&apos;t appear within a few minutes.</p>
                	<p>Click <a href="#" id="resendverify">here</a> to resend the email.</p>
                
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
                    
                    <p>The next step is to add the emails of your contacts.</p>
                    <p>There are two ways to add emails:</p>
                    <div id="contact-list-id">
                    	<ul>
                    		<li>Forward emails that include your contacts in the to-field or cc-field.</li>
                    		<li>Add emails directly as the list.</li>
                    	</ul>
                    </div>
                </section>

                <h2>Set Your Availability and Preferences</h2>
                <section>
                    <p>Morbi ornare tellus at elit ultrices id dignissim lorem elementum. Sed eget nisl at justo condimentum dapibus. Fusce eros justo, 
                        pellentesque non euismod ac, rutrum sed quam. Ut non mi tortor. Vestibulum eleifend varius ullamcorper. Aliquam erat volutpat. 
                        Donec diam massa, porta vel dictum sit amet, iaculis ac massa. Sed elementum dui commodo lectus sollicitudin in auctor mauris 
                        venenatis.</p>
                </section>

                <h2>Send Out Invite</h2>
                <section>
                    <p>Quisque at sem turpis, id sagittis diam. Suspendisse malesuada eros posuere mauris vehicula vulputate. Aliquam sed sem tortor. 
                        Quisque sed felis ut mauris feugiat iaculis nec ac lectus. Sed consequat vestibulum purus, imperdiet varius est pellentesque vitae. 
                        Suspendisse consequat cursus eros, vitae tempus enim euismod non. Nullam ut commodo tortor.</p>
                </section>
                <h2>Check Out Status</h2>
                <section>
                    <p>Quisque at sem turpis, id sagittis diam. Suspendisse malesuada eros posuere mauris vehicula vulputate. Aliquam sed sem tortor. 
                        Quisque sed felis ut mauris feugiat iaculis nec ac lectus. Sed consequat vestibulum purus, imperdiet varius est pellentesque vitae. 
                        Suspendisse consequat cursus eros, vitae tempus enim euismod non. Nullam ut commodo tortor.</p>
                </section>
                <h2>Save the Date</h2>
                <section>
                    <p>Quisque at sem turpis, id sagittis diam. Suspendisse malesuada eros posuere mauris vehicula vulputate. Aliquam sed sem tortor. 
                        Quisque sed felis ut mauris feugiat iaculis nec ac lectus. Sed consequat vestibulum purus, imperdiet varius est pellentesque vitae. 
                        Suspendisse consequat cursus eros, vitae tempus enim euismod non. Nullam ut commodo tortor.</p>
                </section>
            </div>
        </div>
        
        <!-- Scripts -->
		<script src="../js/skel.min.js"></script>
		<script src="../js/util.js"></script>
		<!--[if lte IE 8]><script src="../js/ie/respond.min.js"></script><![endif]-->
		<script src="../js/main.js"></script>
		<script src="../js/login.js"></script>
        
    </body>
</html>
<!DOCTYPE HTML>
<!--
	Typify by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
	String username = (String)session.getAttribute("username");
	if (username == null) {
		// check cookie only if no session context
		Util.updateSessionIfCookie(request);
		username = (String)session.getAttribute("username");
	}
	
	if (username != null) {
		User user = UserAccountHandler.find(username);
		if (user == null) {
			// in this case, do a sign out.
			request.getSession().invalidate();
	    	Util.removeSessionCookie(response);
		}
		else if (user.isFirstTime()) {
			// forward to wizard.jsp
			pageContext.forward("/member/wizard.jsp");
		}
		else {
			// forward to member.jsp
			pageContext.forward("/member/member.jsp");
		}
	}

%>
<html>
	<head>
		<title>Happy Hour Planner</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="../js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="../css/standard.css" />
		<link rel="stylesheet" href="../css/login.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="../css/ie9.css" /><![endif]-->
	</head>
	<body>

		<!-- Banner -->
			<section id="banner">
				<h2><strong>Happy Hour Planner</strong></h2>
				<p>An easier way to organize Happy Hours</p>
				<ul class="actions">
					<li><a href="#login-box" class="button special login-window">Log In</a></li>
					<li><a href="#login-box" class="button special signup-window">Sign Up</a></li>
				</ul>
			</section>

		<!-- One -->
			<section id="one" class="special">
				<h1>&nbsp;</h1>
				<div class="inner">
					<header class="major">
						<h2>Here's how it works:</h2>
					</header>
					<div class="features">
						<div class="feature">
							<i class="fa fa-copy"></i>
							<h3>1. Create a contact list</h3>
							<p>Add a list of emails (either by forwarding an email or by copying a list of emails)</p>
						</div>
						<div class="feature">
							<i class="fa fa-cog"></i>
							<h3>2. Set up your availability for the Happy Hour</h3>
							<p>Specify a list of possible dates and specify one or more locations.</p>
						</div>
						<div class="feature">
							<i class="fa fa-envelope-o"></i>
							<h3>3. Send out the invite</h3>
							<p>Each person receives an individual email with the ability to vote on the location and the date.</p>
						</div>
						<div class="feature">
							<i class="fa fa-user"></i>
							<h3>4. Your friends vote</h3>
							<p>Each person votes and the date/time that accommodates the most people is used.</p>
						</div>
						<div class="feature">
							<i class="fa fa-save"></i>
							<h3>5. Save the Date</h3>
							<p>An email goes out.  The Happy Hour is planned.  See you there.</p>
						</div>
					</div>
				</div>
			</section>

		<!-- Two -->
			<section id="two" class="wrapper style2 special">
				<div class="inner narrow">
					<header>
						<h2>Ready to try it out?</h2>
					</header>
					<form class="grid-form login-window" method="post" action="#login-box">
						<ul class="actions">
							<li><input value="Sign Up" type="submit"></li>
						</ul>
					</form>
				</div>
			</section>
			
			<div id="login-box" class="login-popup">
				<a href="#" class="close"><img src="../images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
  				<form method="post" class="signin" action="#">
        			<fieldset class="textbox">
        				<label class="username">
        					<span>email address</span>
        					<input id="username" name="username" value="" type="text" autocomplete="on" placeholder="Username">
        				</label>
        				<label class="password">
        					<span>Password</span>
        					<input id="password" name="password" value="" type="password" placeholder="Password">
        				</label>
        				<label class="password" id="password2-label">
        					<span>Re-enter Password</span>
        					<input id="password2" name="password2" value="" type="password" placeholder="Password">
        				</label>
        				<button class="submit button-login" id='the-button-text' type="button">Sign up</button>
        				<p>
        					<a class="forgot" href="#" id='login-message'>Forgot your password?</a>
        				</p>        
        			</fieldset>
  				</form>
			</div>
			
		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					&copy; Professional Designs.
				</div>
			</footer>

		<!-- Scripts -->
			<script src="../js/jquery.min.js"></script>
			<script src="../js/skel.min.js"></script>
			<script src="../js/util.js"></script>
			<!--[if lte IE 8]><script src="../js/ie/respond.min.js"></script><![endif]-->
			<script src="../js/main.js"></script>
			<script src="../js/login.js"></script>

	</body>
</html>
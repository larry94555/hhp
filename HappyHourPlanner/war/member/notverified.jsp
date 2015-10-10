<!DOCTYPE HTML>
<!--
	Typify by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<%
	// update check for cookie and update session
	// check for a cookie
	// if cookie found, update session
	
%>
<html>
	<head>
		<title>Happy Hour Planner</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="../js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="../css/main.css" />
		<link rel="stylesheet" href="../css/header.css" />
		<link rel="stylesheet" href="../css/login.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="../css/ie9.css" /><![endif]-->
	</head>
	<body class="landing">
	
		<% boolean loggedIn = (session.getAttribute("username") != null); %>

		<jsp:include page="header.jsp" flush="true" />
		<!-- Banner -->
			<section id="banner">
				<h2><strong>Happy Hour Planner</strong></h2>
				<p id="main-message">You are not yet verified.  Check your email to activate your account.</p>
				<ul class="actions">
					<% if (!loggedIn) { %>
						<li><a href="#login-box" class="button special login-window">Log In</a></li>
						<li><a href="#login-box" class="button special signup-window">Sign Up</a></li>
					<% } %>
				</ul>
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
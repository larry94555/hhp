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
	final User user = Util.checkForUser(request,response);
	if (user != null) {
		//if (user.isFirstTime()) {
			// forward to wizard.jsp
			pageContext.forward("/member/wizard.jsp");
		//}
		//else {
			// forward to member.jsp
			//pageContext.forward("/member/member.jsp");
		//}
	}
%>
<html>
	<head>
		<title>Happy Hour Planner</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="/css/standard.css" />
		<link rel="stylesheet" href="/css/login.css" />
		<link rel="stylesheet" href="/css/header.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="/css/ie9.css" /><![endif]-->
	</head>
	<body class="landing">
	
		<jsp:include page="header.jsp" flush="true" />

		<!-- Banner -->
			<section id="banner">
				<h2><strong>Happy Hour Planner</strong></h2>
				<p>An easier way to organize Happy Hours</p>
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
			
		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					&copy; Professional Designs.
				</div>
			</footer>

		<!-- Scripts -->
			<script src="/js/jquery.min.js"></script>
			<script src="/js/skel.min.js"></script>
			<script src="/js/util.js"></script>
			<!--[if lte IE 8]><script src="/js/ie/respond.min.js"></script><![endif]-->
			<script src="/js/main.js"></script>
			<script src="/js/login.js"></script>

	</body>
</html>
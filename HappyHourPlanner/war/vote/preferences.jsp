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
		<link rel="stylesheet" href="../css/standard.css" />
		<link rel="stylesheet" href="../css/header.css" />
		<link rel="stylesheet" href="../css/login.css" />
		<link rel='stylesheet' href='../css/fullcalendar.css' />
		<script src='../js/jquery.min.js'></script>
		<script src='../js/moment.min.js'></script>
		<script src='../js/fullcalendar.js'></script>
		<!--[if lte IE 9]><link rel="stylesheet" href="../css/ie9.css" /><![endif]-->
		<script>
		$(document).ready(function() {

    		// page is now ready, initialize the calendar...

    		$('#calendar').fullCalendar({
        	// put your options and callbacks here
    		})

		});
		</script>
	</head>
	<body class="landing">
	
		<% boolean loggedIn = (session.getAttribute("username") != null); %>

		<jsp:include page="../member/header.jsp" flush="true" />
		<!-- Banner -->
			<section id="banner">
				<h2><strong>Happy Hour Planner</strong></h2>
			</section>
			
			
			
			<div id='calendar'></div>
			
		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					&copy; Professional Designs.
				</div>
			</footer>

		<!-- Scripts -->
			<!--[if lte IE 8]><script src="../js/ie/respond.min.js"></script><![endif]-->
			<script src="../js/main.js"></script>

	</body>
</html>
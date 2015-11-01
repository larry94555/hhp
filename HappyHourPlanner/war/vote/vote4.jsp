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
		<link rel="stylesheet" href="../css/vote4.css" />
		<link rel="stylesheet" href="../css/classic.css" />
		<link rel="stylesheet" href="../css/classic_date.css"/>
		<link rel="stylesheet" href="../css/classic_time.css"/>
		<!--[if lte IE 9]><link rel="stylesheet" href="../css/ie9.css" /><![endif]-->
	</head>
	<body class="landing">
	
		<% boolean loggedIn = (session.getAttribute("username") != null); %>

		<jsp:include page="../member/header.jsp" flush="true" />
		<!-- Banner -->
			<section id="banner">
				<h2><strong>Happy Hour Planner</strong></h2>
			</section>
			<section id="enter-name">
				<input placeholder="Enter name..."/>
			</section>
			<section>
				<input class="datepicker" type="text" placeholder="Try me…" name="" readonly="" aria-haspopup="true" aria-expanded="true" aria-readonly="false">
			</section>
			<section>
				<input class="timepicker" type="text" placeholder="Try me…" name="" readonly="" aria-haspopup="true" aria-expanded="false" aria-readonly="false">
			</section>
			
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
			<script src="../js/stretchy.js"></script>
			<script src="../js/picker.js"></script>
			<script src="../js/picker.time.js"></script>
			<script src="../js/picker.date.js"></script>
			<!--[if lte IE 8]><script src="../js/ie/respond.min.js"></script><![endif]-->
			<script src="../js/main.js"></script>
			<script>
				$('.datepicker').pickadate();
				$('.timepicker').pickatime();
			</script>

	</body>
</html>
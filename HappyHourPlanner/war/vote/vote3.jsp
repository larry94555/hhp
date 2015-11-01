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
		<link rel="stylesheet" href="../css/vote.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="../css/ie9.css" /><![endif]-->
	</head>
	<body class="landing">
	
		<% boolean loggedIn = (session.getAttribute("username") != null); %>

		<jsp:include page="../member/header.jsp" flush="true" />
		<!-- Banner -->
			<section id="banner">
				<h2><strong>Happy Hour Planner</strong></h2>
			</section>
			
			<section>
				<!-- Name -->
				<label>Name:</label><input type="text" />
				
				<!-- Availability -->
				<label>Availability:</label><select>
					<option>Suggest a date</option>
					<option>Certain days of the week</option>
					<option>The following dates will work</option>
					<option>Unavailable until date</option>
					<option>Unavailable starting from</option>
				</select>
				
				<!-- Location -->
				<label>Location:</label><select>
					<option>Pick a City</option>
					<option>Pick a Neighborhood</option>
					<option>Pick a zip code</option>
					<option>Suggest Multiple Places</option>
				</select>
				<!-- Place -->
				<label>Place:</label><select>
					<option>Suggest a place</option>
					<option>Select from List</option>
				</select>
				
				<!-- Time -->
				<label>Time</label><input type="text" />
			</section>
			<section>
				<div id="container">
      				<span id="question">What is your favorite server side language?</span>
      				<div><span>0</span><a href="">Vote</a>PHP</div>
      				<div><span>0</span><a href="">Vote</a>Ruby</div>
      				<div><span>0</span><a href="">Vote</a>Java</div>
      				<div><span>0</span><a href="">Vote</a>ASP</div>
      				<div><span>0</span><a href="">Vote</a>Perl</div>
      				<div><span>0</span><a href="">Vote</a>ColdFusion</div>
  				</div>
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
			<!--[if lte IE 8]><script src="../js/ie/respond.min.js"></script><![endif]-->
			<script src="../js/main.js"></script>
			<script src="../js/vote.js"></script>

	</body>
</html>
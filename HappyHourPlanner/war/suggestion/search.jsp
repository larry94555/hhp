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
				<Label>City or Zip Code or Cross Street</label>
				<input type="text" name="place" id="place" />
				
				<Label>Category of Place</label>
				<select>
					<option>Bar</option>
					<option>Brewery</option>
					<option>Cafe</option>
					<option>Cocktail Bar</option>
					<option>Dive Bar</option>
					<option>Gastro Pub</option>
					<option>Jazz/Blues</option>
					<option>Karaoke</option>
					<option>Lounge</option>
					<option>Music</option>
					<option>Pool Hall</option>
					<option>Pub</option>
					<option>Restaurant</option>
					<option>Sports Bar</option>
					<option>Wine Bar</option>
				</select>
				
				<input type="button" id="search-button" value="search" />
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
			<script src="../js/login.js"></script>
			
			<script>
				$('input#search-button').click(function() {
					// send out ajax
					
				});
			</script>

	</body>
</html>




<!DOCTYPE HTML>
<!--
	Typify by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>Happy Hour Planner</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="../js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="../css/standard.css" />
		<link rel="stylesheet" href="../css/header.css" />
		<link rel="stylesheet" href="../css/login.css" />
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  		<link rel="stylesheet" href="../schedule-css/autocomplete.css" />
  		<link rel="stylesheet" href="../schedule-css/vote-poll.css"/>
  		<link rel="stylesheet" href="../grid-css/slick.grid.css" type="text/css"/>
  		<link rel="stylesheet" href="../grid-css/jquery-ui-1.8.16.custom.css" type="text/css"/>
  		<link rel="stylesheet" href="../grid-css/examples.css" type="text/css"/>
  		<link rel="stylesheet" href="../vote-css/scheduler.css"/>
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
				<div class="ui-widget">
  					<label for="names">Name: </label>
  					<input id="names" placeholder="Enter name">
				</div>
			</section>
			
			<section>
				<div id="vote-container">
      				<span class="question">Where?</span>
      				<div><span>0</span><a href="">Vote</a>Tied House (MH)</div>
      				<div><span>0</span><a href="">Vote</a>Old Wagon Wheel (SJ)</div>
      				<div><span>0</span><a href="">Vote</a>Farmer's Union (SJ)</div>
      				<div><span>0</span><a href="">Vote</a>Mosaic's (SJ)</div>
				</div>
			</section>
			
			<section>
			<div class="ui-widget">
  					<input id="city" placeholder="Suggest another city"/>
  					<input id="place" placeholder="Suggest another place"/>
				</div>
			</section>
			
			<section>
				<div id="schedule-container">
					<span class="question">When? (Specify by date or day of week)</span>
					<table width="100%">
					  <tr>
					    <td valign="top" width="50%">
					      <div id="myGrid" style="width:670px;height:535px;"></div>
					    </td>
					  </tr>
					</table>
				</div>
			</section>
			
		<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					&copy; Professional Designs.
				</div>
			</footer>

		<!-- Scripts -->
		 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
			<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
			<script src="../js/login.js"></script>
			<script src="../vote-js/name-autocomplete.js"></script>
			<script src="../grid-lib/jquery.event.drag-2.2.js"></script>
			<script src="../grid-lib/slick.core.js"></script>
			<script src="../grid-lib/slick.grid.js"></script>
			<script src="../vote-js/scheduler.js"></script>
			<!--[if lte IE 8]><script src="../js/ie/respond.min.js"></script><![endif]-->

	</body>
</html>
<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
	// check for cookie
	Util.updateSessionIfCookie(request);

	boolean loggedIn = (session.getAttribute("username") != null); 
%>

		<!-- Header -->
		<header id="header">
			<h1>
				<a href="#" id="hello-user">
					<% if (loggedIn) { %>
						<%= session.getAttribute("username") %>
					<% } %>
				</a>
			</h1>
			<nav id="nav">
				<ul>
					<li>
						<% if (loggedIn) { %>
							<a href="#" class="sign-out-button special button">Sign Out</a>
						<% } %>
					</li>
				</ul>
			</nav>
		</header>
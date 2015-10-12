<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
	// check for cookie
	final User user = Util.checkForUser(request,response);
%>

		<!-- Header -->
		<header id="header">
			<h1>
				<a href="#" id="hello-user">
					<% if (user != null) { %>
						<%= user.getUserName() %>
					<% } %>
				</a>
			</h1>
			<nav id="nav">
				<ul>
					<li>
						<% if (user != null) { %>
							<a href="#" class="sign-out-button special button">Sign Out</a>
						<% } %>
					</li>
				</ul>
			</nav>
		</header>
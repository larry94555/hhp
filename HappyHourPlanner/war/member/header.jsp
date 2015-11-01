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
						<% } else { %>
							<a id="login-in-top-link" href="#login-box" class="sign-in-button special button">Log In</a> /
							<a id="sign-up-top-link" href="#login-box" class="sign-up-button special button">Sign Up</a>
						<% } %>
					</li>
				</ul>
			</nav>
		</header>
		<div id="login-box" class="login-popup">
			<a href="#" class="close"><img src="../images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
  			<form method="post" class="signin" action="#">
        		<fieldset class="textbox">
        			<p>
        				<span id="error-message">&nbsp;</span>
        			</p>
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
        				<span class="spacing">&nbsp;</span>
        				<a class="forgot" href="#" id='login-message'>Forgot your password?</a>
        			</p>        
        		</fieldset>
  			</form>
		</div>
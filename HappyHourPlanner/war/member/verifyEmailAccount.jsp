<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happyhourplanner.common.Constant" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
	// check for cookie
	final User user = Util.checkForUser(request,response);
	
	final String status = (String)session.getAttribute(Constant.STATUS);
	
	Util.log("status = " + status + ", currentState = " + user.getCurrentState());
	
%>
                
                <% if (user.isVerified()) { %>
                
                	<p>Your account has already been activated.</p>
                	<p>Click <a id="stepOne-continue" href="#">here</a> to continue.</p>
                	
                <% } else if (status == null) { %>	
              
                	
                	<% if (Util.checkDateInMinutes((java.util.Date)session.getAttribute("verify_resend"),30)) { %>
                
                	<p>We&apos;ve resent the email containing a link that will verify your email account.</p>
                	<p>Please check your spam folder if the email doesn&apos;t appear within a few minutes.</p>
                
                	<% } else { %>
                	
                	<p>We&apos;ve sent you an email containing a link that will allow you to verify your email account.</p>
                	<p>Please check your spam folder if the email doesn&apos;t appear within a few minutes.</p>
                	<p>Click <a href="#" id="resendverify">here</a> to resend the email.</p>
                	
                	<% } %>
                
                <% } else if (!status.equals(Constant.BAD_ACTIVATION_CODE)) { %>
                
                	<p>Thanks for verifying your email.  Welcome to Happy Hour Planner!</p>
                	<p>Click <a href=#">here</a> to continue.</p>
                
                <% } else { %>
                
                	<p>The activation code you selected was old. A more recent one was sent.</p>
                	<p>Click <a href="#" id="resendverify">here</a> to resend the email.</p>
                
                	<p>Thanks for verifying your email.  Welcome to Happy Hour Planner!</p>
                	<p>Click <a href=#">here</a> to continue.</p>
                
                
                <% 
                
                	session.removeAttribute(Constant.STATUS);
                
                	} 
                	
                %>
                
                
                    <div class="messagepop pop">
  						<form method="post" id="new_message" action="/messages">
    						<p><label for="email">Your email address</label><input type="text" name="email-contact" id="email-contact" /></p>
    						<p><label for="body">Message</label><textarea rows="6" name="contact-body" id="contact-body"></textarea></p>
    						<p><input type="submit" value="Send Message" name="comment_submit" id="comment_submit"/></p>
  						</form>
					</div>
					
					<a href="#" id="contact" class="special button">Contact Us</a>
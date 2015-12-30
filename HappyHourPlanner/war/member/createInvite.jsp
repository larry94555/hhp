<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.model.Invite" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happyhourplanner.common.Constant" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
	// check for cookie
	final User user = Util.checkForUser(request,response);
	
	Invite invite = UserAccountHandler.getDefaultInvite(user,pageContext.getServletContext());
%> 

	
 <div id="create-invite-container">
 
 	<div id="create-invite-form">
 
 		<p>
 		<label for="invite-title">Invite Group Name:</label>
 		<input type="text" name="invite-title" id="invite-title" class="invite-setting" value="<%= invite.getGroupName() %>" />
 		<input type="hidden" name="invite-group-id" id="invite-group-id" value="<%=invite.getGroupId() %>" />	
		</p>    
 
 
		<div id='invite-to-container'>
			<label for='invite-to-field'>Add Emails:</label>
			<input type='text' id='invite-to-field' name='invite-to-field' class='form-control invite-setting' value='<%= invite.getInviteesAsString() %>'>
		</div>
 		
    	<p>
    	<label for="invite-draft-text">Text:</label>
    	<textarea class="yellow-page invite-setting" id="invite-draft-text">
    		<%= invite.getText() %>
    	</textarea>
    	</p>            
                    
        <a href="#" id="action-send-invite" class="special button">Send</a>
	
	</div>                    
</div>
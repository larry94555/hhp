<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.controller.ContactHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.model.Invite" %>
<%@ page import="com.happyhourplanner.model.InvitationKey" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happyhourplanner.common.Constant" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>

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
 		<input type="hidden" name="invite-instance-id" id = "invite-instance-id" value="<%=invite.getInviteInstanceId() %>" />
 		<input type="hidden" name="invite-html" id="invite-html" value="<%= StringEscapeUtils.escapeHtml4(invite.getHtml()) %>" />
		</p>  
		
		<p>
		<label for="invite-subject">Invite Subject:</label>
 		<input type="text" name="invite-subject" id="invite-subject" class="invite-setting" value="<%= invite.getSubject() %>" />
 		<% for (InvitationKey invitationKey : ContactHandler.getInvitationKeysAsList(user,invite.getInvitees(),invite.getInviteInstanceId())) { %>
 		
 			<% final String id = Util.convertEmailToId(invitationKey.getEmail()); %>
 		
 		<input type="hidden" name="status-<%= id %>" id="status-<%= id %>" value="<%= invitationKey.getState() %>" />
 		
 		<% } %>
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
                    
        <% if (invite.getInviteesAsString().trim().length() == 0) { %>
        
        <a href="#" id="action-send-invite" class="special button disabled">Send</a>
        
        <% } else { %>
        
        <a href="#" id="action-send-invite" class="special button">Send</a>
	
	
		<% } %>
	</div>                    
</div>
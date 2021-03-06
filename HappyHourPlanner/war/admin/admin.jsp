<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 

	final User user = Util.checkForUser(request,response);
	if (user == null || !user.getUserName().equalsIgnoreCase("larry.freeman@gmail.com")) {
		response.sendRedirect("/");
		return;
	}

%>

<html>
	<head>
		<title>Admin Page</title>
		<!-- DataTables CSS -->
	<link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.css">
	<link rel="stylesheet" type="text/css" href="../css/select.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="../css/admin.css">
	<link rel="stylesheet" href="../css/edit.table.css" />
	<style type="text/css" class="init">

	</style>
	<script type="text/javascript" language="javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" language="javascript" src="../js/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" src="../js/datatables.select.min.js"></script>
	<script type="text/javascript" language="javascript" src="../js/admin.js"></script>		
	</head>
	<body>
	
		<%
			int count = UserAccountHandler.getUserCount();
		%>
	
		<p>User Name = <%= session.getAttribute("username") %></p>
		<p>Current url = ${pageContext.request.requestURL}</p>
		<p></p>
		
		
		<p>User Count = <%= count %></p>
	
		<table id="table_id" class="display">
    		<thead>
        		<tr>
            		<th>Id</th>
            		<th>Username</th>
            		<th>Verified</th>
            		<th>Disabled</th>
            		<th>Change Password</th>
            		<th>First Time User</th>
            		<th>Current State</th>
            		<th>Email Text Only</th>
        		</tr>
    		</thead>
    		<tbody>
    		<%
    		
    			List<User> users = UserAccountHandler.getAllUsers(0,count);
    			int i=0;
    			for (User each : users) { 
    				i++;
    			
    			%>
    			
    			<tr>
            		<td class="row_id"><%= i %></td>
            		<td class="row_email"><%= each.getUserName() %></td>
            		<td class="row_verified"><%= each.isVerified() %></td>
            		<td class="row_disabled"><%= each.isDisabled() %></td>
            		<td class="row_change_password"><%= each.needToChangePwd() %></td>
            		<td class="row_first_time_user"><%= each.isFirstTime() %></td>
            		<td class="row_current_state" data-value="<%= each.getCurrentState() %>"><%= each.getCurrentStateAsString() %></td>
            		<td class="row_email_text_only"><%= each.emailTextOnly() %></td>
        		</tr>
    			
    			
    		<%	} %>
  
    		</tbody>
		</table>
		
		<div id="edit-table-box" class="edit-table-popup">
			<a href="#" class="close-popup"><img src="../images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
  			<form method="post" class="edit-table" action="#">
        		<fieldset class="textbox">
        			<label class="username">
        				<span>email address</span>
        				<span id="edit-table-username"></span>
        			</label>
        			<label class="user-verified" id="user-verified-label">
        				<span>Verified?</span>
        				<select id="edit-table-verified" name="user-verified">
        					<option value="true">true</option>
        					<option value="false">false</option>
        				</select>
        			</label>
        			<label class="user-disabled" id="user-disabled-label">
        				<span>Disabled?</span>
        				<select id="edit-table-disabled" name="user-disabled">
        					<option value="true">true</option>
        					<option value="false">false</option>
        				</select>
        			</label>
        			<label class="change-password" id="change-password-label">
        				<span>Change Password?</span>
        				<select id="edit-table-change-password" name="user-change-password">
        					<option value="true">true</option>
        					<option value="false">false</option>
        				</select>
        			</label>
        			<label class="user-first-time" id="user-first-time-label">
        				<span>First Time User?</span>
        				<select id="edit-table-first-time" name="user-first-time">
        					<option value="true">true</option>
        					<option value="false">false</option>
        				</select>
        			</label>
        			<label class="user-current-state" id="user-current-state-label">
        				<span>Current State</span>
        				<select id="edit-table-current-state" name="user-current-state">
        					<option value="0">verify email</option>
        					<option value="1">preferences</option>
        					<option value="2">invite</option>
        					<option value="3">status</option>
        					<option value="4">date</option>
        					<option value="5">undefined</option>
        				</select>
        			</label>
        			<label class="user-email-text-only" id="user-email-text-only-label">
        				<span>Email Text Only</span>
        				<select id="edit-table-email-text-only" name="user-email-text-only">
        					<option value="true">true</option>
        					<option value="false">false</option>
        				</select>
        			</label>
        			<label class="user-delete" id="user-delete-label">
        				<span>Delete?</span>
        				<select id="edit-table-delete" name="user-delete">
        					<option value="yes">yes</option>
        					<option value="no">no</option>
        				</select>
        			</label>
        			<button class="commit-change button-edit-table" id='the-button-text' type="button">Commit</button>
        			<p>
        				<a class="message" href="#" id='edit-table-message'>No message at this time.</a>
        			</p>        
        		</fieldset>
  			</form>
		</div>
		
		<div class="messagepop pop">
  			<form method="post" id="new_message" action="/messages">
    			<p><label for="email">Your email or name</label><input type="text" size="30" name="email" id="email" /></p>
    			<p><label for="body">Message</label><textarea rows="6" name="body" id="body" cols="35"></textarea></p>
    			<p><input type="submit" value="Send Message" name="commit" id="message_submit"/> or <a class="close" href="/">Cancel</a></p>
  			</form>
		</div>
		
		<a href="/contact" id="contact">Contact Us</a>
		
	</body>
</html>
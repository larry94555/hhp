<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 

	String username = (String)session.getAttribute("username");
	if (username == null) {
		// check cookie only if no session context
		Util.updateSessionIfCookie(request);
		username = (String)session.getAttribute("username");
	}
	if (username == null || !username.equalsIgnoreCase("larry.freeman@gmail.com")) {
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
        		</tr>
    		</thead>
    		<tbody>
    		<%
    		
    			List<User> users = UserAccountHandler.getAllUsers(0,count);
    			int i=0;
    			for (User user : users) { 
    				i++;
    			
    			%>
    			
    			<tr>
            		<td class="row_id"><%= i %></td>
            		<td class="row_email"><%= user.getUserName() %></td>
            		<td class="row_verified"><%= user.isVerified() %></td>
            		<td class="row_disabled"><%= user.isDisabled() %></td>
            		<td class="row_change_password"><%= user.needToChangePwd() %></td>
            		<td class="row_first_time_user"><%= user.isFirstTime() %></td>
            		<td class="row_current_state" data-value="<%= user.getCurrentState() %>"><%= user.getCurrentStateAsString() %></td>
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
        					<option value="1">contact list</option>
        					<option value="2">preferences</option>
        					<option value="3">invite</option>
        					<option value="4">status</option>
        					<option value="5">date</option>
        					<option value="6">undefined</option>
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
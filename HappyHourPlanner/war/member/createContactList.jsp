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
%>


                	<% if (status != null && status.equals(Constant.JUST_ACTIVATED)) { %>
                
                    <p>Thanks for confirming your email address!</p>
                   
                    
                    <% } %>
                    
                    <p>The next step is to add your contacts to the table below and click 'add'.</p>
                    <p>You need at least 1 contact.<span id='contact-continue'> When done, click <a id='stepTwo-continue' href='#'>here</a> to continue.</span></p>
                    
                    <div class="add-contact-list-area">
                    
                    	<button id='add-contact-button' class='slider-button' title='add a contact.'>add</button>
                    	<button id='email-contact-button' class='slider-button' title='forward an email'>email the contact</button>
                    	<button id='edit-contact-button' class='slider-button' title='edit existing list'>edit list</button> 
                    	<span id='edit-contact-recycle-bin' class='glyphicon glyphicon-trash'>2</span>
					  	<table id="mainTable" class="table table-striped">
            				<thead><tr><th>Name</th><th>Email Address</th></tr></thead>
            				<tbody>
            					<% for (int i=0; i < 10; i++) { %>
            					
              					<tr><td class='contact-name'>&nbsp;</td><td class='contact-email'>&nbsp;</td></tr>
              					
              					<% } %>
            				</tbody>
          				</table>
					</div>
                    <div id="slider">
					  <div class="slider-content">
					  	<ul id='slider-contact-list'>
					  
					  <%= UserAccountHandler.generateContactListHtml(user) %>   
					    
					  	
					  	</ul>
					  </div>
					 
					</div>
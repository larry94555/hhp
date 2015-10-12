package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.model.EM;
import com.happyhourplanner.model.User;

public class UpdateUserServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(UpdateUserServlet.class.getName());
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		
		
		//_log.info("*** Entering UpdateUserServlet. ***");
		
	    resp.setContentType("text/plain");
	    
	

	    String username = req.getParameter("username");
	    PrintWriter out = resp.getWriter();
	    
	    User user = UserAccountHandler.find(username);
	    if (user == null) {
	    	//_log.info("User not found: " + username);
	    	out.println("User not found.");
	    	out.close();
	    	return;
	    }
	    
	    String delete = req.getParameter("delete");
	    
	    //_log.info("username = " + username + ", delete = " + delete);
	    
	    if (delete.equals("yes")) {
	    	
	    	_log.info("attempting to delete");
	    	
	    	
	    	EM.get().remove(user);
	    	EM.commit();
	    	out.println("deleted");
	    	out.close();
	    	return;
	    }
	    
	    _log.info("did not delete");
	    
	    String verified = req.getParameter("verified");
	    if (verified.equals("true")) {
	    	user.setVerifiedFlag(true);
	    }
	    else if (verified.equals("false")) {
	    	user.setVerifiedFlag(false);
	    }
	    
	    String disabled = req.getParameter("disabled");
	    if (disabled.equals("true")) {
	    	user.setDisabledFlag(true);
	    }
	    else if (disabled.equals("false")) {
	    	user.setDisabledFlag(false);
	    }
	    
	    String changePassword = req.getParameter("changePassword");
	    if (changePassword.equals("true")) {
	    	user.setChangePassword(true);
	    }
	    else if (changePassword.equals("false")) {
	    	user.setChangePassword(false);
	    }
	    
	    String firstTimeUser = req.getParameter("firstTimeUser");
	    if (firstTimeUser.equals("true")) {
	    	user.setFirstTimeUser(true);
	    }
	    else if (firstTimeUser.equals("false")) {
	    	user.setFirstTimeUser(false);
	    }
	    
	    String currentStateStr = req.getParameter("currentState");
	    try {
	    	int currentState = Integer.parseInt(currentStateStr);
	    
		    if (currentState >=0 && currentState <= 6) {
		    	user.setCurrentState(currentState);
		    }
	    }
	    catch(NumberFormatException e) {
	    	// in this case, make no change.
	    }
	    
	    String emailTextOnly = req.getParameter("emailTextOnly");
	    if (emailTextOnly.equals("true")) {
	    	user.setEmailTextOnly(true);
	    }
	    else if (emailTextOnly.equals("false")) {
	    	user.setEmailTextOnly(false);
	    }
	    	    
	    EM.get().persist(user);
	    EM.commit();
	    out.println("updated");
	    out.close();
	    
	
	}
	catch (Exception ex) {
		_log.log(Level.WARNING, "Failure in checking user : " +
	ex.getMessage());
	 }
	}
	

}

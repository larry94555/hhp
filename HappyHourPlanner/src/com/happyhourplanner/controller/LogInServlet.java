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
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class LogInServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(LogInServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		
		//_log.info("Entering LogInServlet: " + req.getSession().getAttribute("username"));
		
		resp.setContentType("application/json");

	    String username = req.getParameter("user");
	    String password = req.getParameter("pwd");
	    
	    PrintWriter out = resp.getWriter();
	    
	    // check if email exists
	    if (UserAccountHandler.exists(username)) {
	    	
	    	//_log.info("UserAccountHandler exists");
	    	
	    	User user = UserAccountHandler.find(username);
	    	if (user.isDisabled()) {
	    		//_log.info(Constant.ACCOUNT_DISABLED);
	    		ResponseBean.println(out, Constant.ACCOUNT_DISABLED);
	    	}
	    	else if (!user.checkPassword(password)) {
	    		//_log.info(Constant.ACCOUNT_EXISTS);
	    		ResponseBean.println(out, Constant.ACCOUNT_EXISTS);
	    	}
	    	else if (!user.isVerified()) {
	    		req.getSession().setAttribute("username",username);
	    		//_log.info(Constant.NEED_TO_VERIFY);
	    		Util.saveSessionAsCookie(req.getSession(),resp,Constant.ACTION_LOGIN);

	    	}
	    	else if (UserAccountHandler.login(username,password)) {
	    		
	    		req.getSession().setAttribute("username",username);
	    		
	    		//_log.info("Sending out email");
	    		//Mailer.mail(Constant.FROM_ADDRESS, Constant.FROM_NAME, "larry.freeman@gmail.com", "Hello from HappyHourPlanner", "Hello!  Welcome!", "Click on this <a href=\"http://localhost:8888/admin/admin.jsp\">link</a>");
	    		
	    		//_log.info("Sent mail");
	    		
	    		Util.saveSessionAsCookie(req.getSession(),resp,Constant.ACTION_LOGIN);
	    	
	    	}
	    	else {
	    		ResponseBean.println(out, Constant.PROBLEM_OCCURRED);
	    	}
	    } 
	    else {
	    	// user doesn't exist
	    	_log.info(Constant.ACCOUNT_NOT_EXIST);
	    	ResponseBean.println(out, Constant.ACCOUNT_NOT_EXIST);
	    }
	    out.close();
	
	}
	catch (Exception ex) {
		_log.log(Level.WARNING, "Failure in logging in user : " +
	ex.getMessage());
		ex.printStackTrace();
		
	 }
	}

}

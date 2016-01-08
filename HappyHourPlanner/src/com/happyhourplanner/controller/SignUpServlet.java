package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.apphosting.utils.config.ClientDeployYamlMaker.Request;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class SignUpServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(SignUpServlet.class.getName());
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		
	    resp.setContentType("application/json");

	    String username = req.getParameter("username");
	    String password = req.getParameter("pwd");
	    String password2 = req.getParameter("pwd2");
	    
	    //_log.info("Entering SignUpServlet: username = " + username);
	    
	    final User user = UserAccountHandler.find(username);
	    
	    PrintWriter out = resp.getWriter();
	    
	    // validate before trying to add
	    if (!password.equals(password2)) {
	    	ResponseBean.println(out,Constant.PASSWORDS_DONT_MATCH);
	    }
	    else if (!UserAccountHandler.checkIfValidEmailAddress(username)) {
	    	ResponseBean.println(out,Constant.EMAIL_ADDRESS_NOT_VALID);
	    }
	    else if (user != null) {
	    	
	    	//_log.info("UserAccountHandler.exists");
	    	
	    	if (user.isDisabled()) {
	    		ResponseBean.println(out, Constant.ACCOUNT_DISABLED);
	    	}
	    	else if (!user.checkPassword(password)) {
	    		ResponseBean.println(out, Constant.ACCOUNT_EXISTS);
	    	}
	    	else if (!user.isVerified()) {
	    		req.getSession().setAttribute("username",username);
	    		Util.saveSessionAsCookie(req.getSession(),resp,Constant.ACTION_SIGNUP);

	    	}
	    	else if (UserAccountHandler.login(username,password)) {
	    		req.getSession().setAttribute("username",username);
	    		Util.saveSessionAsCookie(req.getSession(),resp,Constant.ACTION_LOGIN);
	    		
	    	}
	    	else {
	    		out.println(Constant.ACCOUNT_EXISTS);
	    	}
	    }
	    
	    // check if email exists
	    else if (UserAccountHandler.signup(username,password)) {
	    	HttpSession session = req.getSession();
	    	session.setAttribute(Constant.USERNAME,username);
	    	Util.saveSessionAsCookie(session,resp,Constant.ACTION_SIGNUP);
	    	
	    	final Map<String,String> propertyMap = new HashMap<String,String>();
	    	propertyMap.put(Constant.ACTIVATION_KEY,UserAccountHandler.getActivationKey(username));
	    	
	    	final String parts[] = username.split("@");
		    
		    propertyMap.put(Constant.NAME_PROPERTY_KEY,parts[0]);
		    propertyMap.put(Constant.EMAIL_PROPERTY_KEY,username);
	    	
	    	// send out email
	    	Mailer.mail(
	    			getServletContext(),
	    			Constant.FROM_ADDRESS, 
	    			Constant.FROM_NAME, 
	    			"larry.freeman@gmail.com", 
	    			Constant.VERIFY_EMAIL_SUBJECT, 
	    			Constant.VERIFY_EMAIL_TEXT_FILE, 
	    			Constant.VERIFY_EMAIL_HTML_FILE,
	    			propertyMap);
	    }
	    else {
	    	//out.println(Constant.PROBLEM_OCCURRED);
	    	ResponseBean.println(out, Constant.PROBLEM_OCCURRED);
	    }
	     
	    out.close();
	
	}
	catch (Exception ex) {
		_log.log(Level.WARNING, "Failure in checking user : " +
	ex.getMessage());
	 }
	}

}

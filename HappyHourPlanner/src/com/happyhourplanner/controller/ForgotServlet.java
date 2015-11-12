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

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class ForgotServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(ForgotServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setContentType("application/json");
		    
		    PrintWriter out = resp.getWriter();
		    final String username = (String) req.getSession().getAttribute(Constant.USERNAME);
		    final User user = UserAccountHandler.validateUser(username,out);
		    
		    
		    if (user != null) {
	    		
	    		final Map<String,String> propertyMap = new HashMap<String,String>();
		    	propertyMap.put(Constant.PASSWORD_RESET_KEY,UserAccountHandler.getPasswordResetKey(username));
		    	
		    	// send out email
		    	Mailer.mail(
		    			getServletContext(),
		    			Constant.FROM_ADDRESS, 
		    			Constant.FROM_NAME, 
		    			"larry.freeman@gmail.com", 
		    			Constant.RESET_PASSWORD_EMAIL_SUBJECT, 
		    			Constant.RESET_PASSWORD_EMAIL_TEXT_FILE, 
		    			Constant.RESET_PASSWORD_EMAIL_HTML_FILE,
		    			propertyMap);
	    		
	    		ResponseBean.println(out, Constant.LINK_SENT_TO_USER);
		   
		    } 
		    out.close();
		
		}
		catch (Exception ex) {
			_log.log(Level.WARNING, "Failure in resending link : " +
					ex.getMessage());
			ex.printStackTrace();
			
		}
	}

}

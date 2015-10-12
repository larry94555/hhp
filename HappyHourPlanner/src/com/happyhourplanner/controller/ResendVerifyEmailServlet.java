package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class ResendVerifyEmailServlet extends HttpServlet {
	
public static final Logger _log = Logger.getLogger(ResendVerifyEmailServlet.class.getName());
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Retrieve information which is only email address
		resp.setContentType("application/json");

	    String username = req.getParameter("username");
	    
	    _log.info("ResendVerifyEmailServlet: username = " + username);
	    
	    PrintWriter out = resp.getWriter();
	    
	    HashMap<String,String> propertyMap = new HashMap<String,String>();
	    
	    propertyMap.put(Constant.ACTIVATION_KEY,UserAccountHandler.getActivationKey(username));
	    
	    User user = UserAccountHandler.find(username);
	    
	    final String html_file_path = (user.emailTextOnly()) ? null : Constant.VERIFY_EMAIL_HTML_FILE;
	    
	    
	 // send out email
    	try {
			Mailer.mail(this.getServletContext(),
					Constant.FROM_ADDRESS, 
					Constant.FROM_NAME, 
					"larry.freeman@gmail.com", 
					Constant.VERIFY_EMAIL_SUBJECT, 
					Constant.VERIFY_EMAIL_TEXT_FILE, 
					html_file_path,
					propertyMap);
			
			_log.info("Mail sent without issue");
			
			ResponseBean.println(out, Constant.NEED_TO_VERIFY);
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}

package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.apphosting.utils.config.ClientDeployYamlMaker.Request;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.User;

public class SignOutServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(SignOutServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		
	    resp.setContentType("text/plain");

	    String username = req.getParameter("user");
	    
	    PrintWriter out = resp.getWriter();
	    
	    // check if email exists
	    if (req.getSession().getAttribute("username").equals(username)) {
	    	req.getSession().removeAttribute("username");
	    	out.println("signout");
	    	req.getSession().invalidate();
	    	Util.removeSessionCookie(resp);
	    	
	    }
	    else {
	    	out.println("nothing");
	    }
	    	
	    out.close();
	
	}
	catch (Exception ex) {
		_log.log(Level.WARNING, "Failure in checking user : " +
	ex.getMessage());
	 }
	}

}

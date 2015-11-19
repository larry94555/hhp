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
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class RemoveContactHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(RemoveContactHandlerServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	
			resp.setContentType("application/json");
			final String username = (String)req.getSession().getAttribute("username");
			PrintWriter out = resp.getWriter();
			final User user = UserAccountHandler.validateUser(username,out);
			if (user != null) {
				
				// remove the contact
				String email = req.getParameter("email");
				
				ContactHandler.remove(user,email);
				ResponseBean.println(out, Constant.CONTACTS_ADDED,"NA",UserAccountHandler.generateContactListHtml(user));
				
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

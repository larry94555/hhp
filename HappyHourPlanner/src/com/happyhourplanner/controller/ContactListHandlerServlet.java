package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.Contact;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class ContactListHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(ContactListHandlerServlet.class.getName());
	private static final Gson _gson = Util.getGson();
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setContentType("application/json");
			final String username = (String)req.getSession().getAttribute("username");
			PrintWriter out = resp.getWriter();
			final User user = UserAccountHandler.validateUser(username,out);
			
			if (user != null) {
				
				// add list to the contacts
				final Contact[] contacts = _gson.fromJson(req.getParameter("list"),Contact[].class);
				UserAccountHandler.addContactList(user, contacts);
				ResponseBean.println(out, Constant.CONTACTS_ADDED);
				
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

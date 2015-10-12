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
import com.happyhourplanner.model.User;

public class ActivationCodeHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(ActivationCodeHandlerServlet.class.getName());
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// get url
			_log.info("path = " + req.getPathInfo());
			
			final String activationCode = req.getPathInfo().substring(1);
			final User user = Util.checkForUser(req,resp);
			
			if (!user.isVerified()) { 
			
				UserAccountHandler.activateUser(activationCode,user);
				if (!user.isVerified()) {
					req.getSession().setAttribute(Constant.STATUS, Constant.BAD_ACTIVATION_CODE);
				}
				else {
					req.getSession().setAttribute(Constant.STATUS, Constant.JUST_ACTIVATED);
				}
			}
			else {
				req.getSession().setAttribute(Constant.STATUS, Constant.BAD_ACTIVATION_CODE);
			}
			
			resp.sendRedirect("/");
			
		}
		catch(Exception e) {
			_log.log(Level.WARNING, "Failure in handling activation code : " +
					e.getMessage());
		}
	}

}

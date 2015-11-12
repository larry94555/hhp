package com.happyhourplanner.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.User;

public class ResetPasswordHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(ResetPasswordHandlerServlet.class.getName());
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// get url
			_log.info("path = " + req.getPathInfo());
			
			final String passwordResetCode = req.getPathInfo().substring(1);
			final User user = Util.checkForUser(req,resp);
			
			if (UserAccountHandler.resetPassword(passwordResetCode,user)) {
				req.getSession().setAttribute(Constant.STATUS, Constant.BAD_PASSWORD_RESET_CODE);
			}
			else {
				req.getSession().setAttribute(Constant.STATUS, Constant.PASSWORD_RESENT);
			}
			
			resp.sendRedirect("/");
			
		}
		catch(Exception e) {
			_log.log(Level.WARNING, "Failure in handling password reset code : " +
					e.getMessage());
		}
	}

}

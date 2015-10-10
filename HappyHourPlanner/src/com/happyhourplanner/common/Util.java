package com.happyhourplanner.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

import com.happyhourplanner.controller.LogInServlet;
import com.happyhourplanner.controller.SavedSessionHandler;
import com.happyhourplanner.model.Passwords;
import com.happyhourplanner.model.ResponseBean;

public class Util {
	
	public static final Logger _log = Logger.getLogger(Util.class.getName());
	
	private Util() {}
	
	public static void log(String message) {
		_log.info(message);
	}
	
	public static String generateActivationKey() {
		return Passwords.generateRandomPassword(Constant.ACTIVATION_KEY_LENGTH);
	}
	
	public static String getCookieValue(final HttpServletRequest request, final String name) {
		
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	    	HttpSession session = request.getSession();
	    	_log.info("Found " + cookies.length + " cookie(s).");
	        for (Cookie cookie : cookies) {
	        	_log.info("cookie: " + cookie.getName() + ", value = " + cookie.getValue() + ", name = " + name + ", session = " + session.getId());
	            if (name.equals(cookie.getName())) {
	                return cookie.getValue();
	            }
	        }
	    }
	    return null;
		
	}
	
	public static void updateCurrentSessionAttribute(HttpSession currentSession,Map updateSessionAttributes,
			String attribute) {
		currentSession.setAttribute(attribute,updateSessionAttributes.get(attribute));
	}
	
	public static void updateCurrentSession(HttpSession currentSession,Map updateSessionAttributes) {
		// for now, just update username
		if (!updateSessionAttributes.isEmpty()) {
			updateCurrentSessionAttribute(currentSession,updateSessionAttributes,Constant.USERNAME);
		}
	}
	
	public static void updateSessionIfCookie(final HttpServletRequest request) {
		// if session exists, return session.
		String sessionIdStr = getCookieValue(request,Constant.COOKIE_NAME);
		_log.info("updateSessionIfCookie: sessionIdStr = " + sessionIdStr);
		Map sessionAttributes = SavedSessionHandler.getSessionAttributes(sessionIdStr);
		if (!sessionAttributes.isEmpty()) {
			_log.info("oldSession Username: " +  sessionAttributes.get(Constant.USERNAME));
			updateCurrentSession(request.getSession(),sessionAttributes);
		}
	}
	
	public static void addCookie(final HttpServletResponse response, 
								 final String name, 
								 final String value, 
								 final int maxAge) {
	    Cookie cookie = new Cookie(name, value);
	    cookie.setPath("/");
	    cookie.setMaxAge(maxAge);
	    _log.info("adding cookie with name: " + name + ", value = " + value);
	    response.addCookie(cookie);
	}
	
	public static void saveSessionAsCookie(
											final HttpSession session,
											final HttpServletResponse response
								) throws IOException {
		
		saveSessionAsCookie(session,response,null);
		
	}
	
	public static void saveSessionAsCookie(
			final HttpSession session,
			final HttpServletResponse response,
			final String action
			) throws IOException {

		final String username=(String)session.getAttribute(Constant.USERNAME);
		_log.info("saveSessionAsCookie: username = " + username);
		// only save if there is a user context
		if (username!=null) {
			// save session
			_log.info("Before SavedSessionHandler.saveSession");
			final String sessionIdStr = SavedSessionHandler.saveSession(session,username);
			_log.info("After SavedSessionHandler.saveSession: sessionIdStr = " + sessionIdStr);
			PrintWriter out = response.getWriter();
			ResponseBean.println(out,action,sessionIdStr);
			//addCookie(response,Constant.COOKIE_NAME,sessionIdStr,Constant.COOKIE_MAX_AGE);

		}
		else if (action != null) {
			PrintWriter out = response.getWriter();
			ResponseBean.println(out, action);	
		}
		
		
	}
	
	public static void removeCookie(HttpServletResponse response, String name) {
	    addCookie(response, name, null, 0);
	}
	
	public static void removeSessionCookie(HttpServletResponse response) {
		removeCookie(response,Constant.COOKIE_NAME);
	}



}

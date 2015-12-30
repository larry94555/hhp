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

import com.google.gson.Gson;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.model.PlaceMarker;
import com.happyhourplanner.model.Preferences;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class InviteHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(InviteHandlerServlet.class.getName());
	private static final Gson _gson = new Gson();
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setContentType("application/json");
		    
		    PrintWriter out = resp.getWriter();
		    final String username = (String) req.getSession().getAttribute(Constant.USERNAME);
		    final User user = UserAccountHandler.validateUser(username,out);
		    
		    
		    if (user != null) {
		    	
		    	// get details from the settings
		    	final String groupName = req.getParameter("groupName");
		    	final String[] toList = _gson.fromJson(req.getParameter("toList"),String[].class);
		    	final String text = req.getParameter("text");
		    	final int groupId = Integer.parseInt(req.getParameter("groupId"));
		    	
		    	
		    	//UserAccountHandler.updatePreferences(user,placeMarkerMap,prefs);
		    	UserAccountHandler.updateInvite(user,groupName,toList,text,groupId,getServletContext());
		    	
	    		
	    		// save preferences
	    		
	    		ResponseBean.println(out, Constant.CHANGES_SAVED);
		   
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

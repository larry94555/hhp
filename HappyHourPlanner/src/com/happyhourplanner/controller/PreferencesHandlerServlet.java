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
import com.happyhourplanner.model.Contact;
import com.happyhourplanner.model.PlaceMarker;
import com.happyhourplanner.model.Preferences;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.User;

public class PreferencesHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(PreferencesHandlerServlet.class.getName());
	private static final Gson _gson = new Gson();
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setContentType("application/json");
		    
		    PrintWriter out = resp.getWriter();
		    final String username = (String) req.getSession().getAttribute(Constant.USERNAME);
		    final User user = UserAccountHandler.validateUser(username,out);
		    
		    
		    if (user != null) {
		    	
		    	// get preferences from the settings
		    	final PlaceMarker[] placeMarkers = _gson.fromJson(req.getParameter("placeMarkers"),PlaceMarker[].class);
		    	Map<String,String> placeMarkerMap = new HashMap<String,String>();
		    	
		    	if (placeMarkers == null) {
		    		_log.info("*** placeMarkers is null");
		    		
		    	}
		    	else if (placeMarkers.length > 0) {
		    	
		    		// build
		    		
			    	for (PlaceMarker placeMarker : placeMarkers) {
			    		placeMarkerMap.put(placeMarker.getId(),placeMarker.toStringForm());
			    		_log.info(placeMarker.debug());
			    	}
		    	}
			    	
		    	final Preferences prefs = _gson.fromJson(req.getParameter("settings"),Preferences.class);
		    	if (prefs == null) {
		    		_log.info("*** prefs is null");
		    	}
		    	else {
		    		_log.info(prefs.debug());
		    	}
		    	UserAccountHandler.updatePreferences(user,placeMarkerMap,prefs);
		    	
	    		
	    		// save preferences
	    		
	    		ResponseBean.println(out, Constant.PREFERENCES_SAVED);
		   
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

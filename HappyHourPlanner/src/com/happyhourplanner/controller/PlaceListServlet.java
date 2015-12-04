package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.yelp.YelpHandler;

public class PlaceListServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(PlaceListServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setContentType("application/json");
		    
		    PrintWriter out = resp.getWriter();
		    
		    // get request
		    ResponseBean.println(out, "test","NA",YelpHandler.getPlaceListAsHtml("dinner","San Francisco, CA"));
		    
		    
		    out.close();
		
		}
		catch (Exception ex) {
			_log.log(Level.WARNING, "Failure in resending link : " +
					ex.getMessage());
			ex.printStackTrace();
			
		}
	}
	

}

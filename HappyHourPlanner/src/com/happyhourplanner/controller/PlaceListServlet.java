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
import com.happyhourplanner.yelp.YelpHandler;

public class PlaceListServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(PlaceListServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			
			// note: longitude and latitude should only be included for detect current location
			String category = req.getParameter("category");
			String location = req.getParameter("location");
			String longitude = req.getParameter("longitude");
			String latitude = req.getParameter("latitude");
			String minRating = req.getParameter("minRating");
			String restaurantsOnly = req.getParameter("restaurantsOnly");
			String fullBar = req.getParameter("fullBar");
			String offset = req.getParameter("offset");
			
			
			if (category == null || category.trim().length() == 0) {
				ResponseBean.println(out,Constant.CATEGORY_MISSING);
			}
			else if (location == null || location.trim().length() == 0) {
				ResponseBean.println(out,Constant.LOCATION_MISSING);
			}
			
			String optionList;
			
			if (longitude == null || latitude == null || longitude.trim().length() == 0 || latitude.trim().length() == 0 ) {
				
				optionList = YelpHandler.getPlaceListAsHtml(category,location,null,null,minRating,restaurantsOnly,
						fullBar,Constant.YELP_REQUEST_LIMIT,offset);
				

			}
			else {
		    
				// get request
				optionList = YelpHandler.getPlaceListAsHtml(category,location,longitude,latitude,minRating,
						restaurantsOnly,fullBar,Constant.YELP_REQUEST_LIMIT,offset);
				
		    
			}
		    
			//_log.info("optionList: " + optionList);
			ResponseBean.println(out,"test","NA",optionList);
		    out.close();
		
		}
		catch (Exception ex) {
			_log.log(Level.WARNING, "Failure in resending link : " +
					ex.getMessage());
			ex.printStackTrace();
			
		}
	}
	

}

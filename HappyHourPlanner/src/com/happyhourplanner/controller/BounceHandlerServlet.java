package com.happyhourplanner.controller;

	import java.io.IOException;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.*;

import com.google.appengine.api.mail.BounceNotification;
import com.google.appengine.api.mail.BounceNotificationParser;

	public class BounceHandlerServlet extends HttpServlet {
		
		public static final Logger _log = Logger.getLogger(BounceHandlerServlet.class.getName());
		
	    @Override
	    public void doPost(HttpServletRequest req,
	                       HttpServletResponse resp)
	            throws IOException {
	    	
	    try {
	       BounceNotification bounce = BounceNotificationParser.parse(req);
	       // The following data is available in a BounceNotification object
	       // bounce.getOriginal().getFrom() 
	       // bounce.getOriginal().getTo() 
	       // bounce.getOriginal().getSubject() 
	       // bounce.getOriginal().getText() 
	       // bounce.getNotification().getFrom() 
	       // bounce.getNotification().getTo() 
	       // bounce.getNotification().getSubject() 
	       // bounce.getNotification().getText() 
	       // ...
	       String text = bounce.getOriginal().getText();
	       String subject = bounce.getOriginal().getSubject();
	       String to = bounce.getOriginal().getTo();
	       
	       String notificationFrom = bounce.getNotification().getFrom();
	       String notificationTo = bounce.getNotification().getTo();
	       String notificationSubject = bounce.getNotification().getSubject();
	       String notificationText = bounce.getNotification().getText();
	       
	       _log.info("*** BounceHandler: text: " + text + ", subject = " + subject + ", to: " + to + ", notificationFrom = " + 
	    		   notificationFrom + ", notificationTo = " + notificationTo + ", notificationSubject = " +
	    		   notificationSubject + ", notificationText = " + notificationText);
	       
	       
	    }
	    catch(MessagingException e) {
	    	throw new IOException(e);
	    }
	   }
	
	
}

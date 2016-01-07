package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class MailHandlerServlet extends HttpServlet {
	
	public static final Logger _log = Logger.getLogger(MailHandlerServlet.class.getName());
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null); 
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			
			//Extract out the important fields from the Mime Message
			String subject = message.getSubject();
			//_log.info("Got an email. Subject = " + subject);
			String contentType = message.getContentType(); 
	
			//_log.info("Email Content Type : " + contentType);
	
			//Parse out the Multiparts
			//Perform business logic based on the email
	
			Address[] replyTo = message.getReplyTo();
	
			//_log.info("replyto: count: " + replyTo.length);
	
			//_log.info("replyto: " + replyTo[0]);
	
			Address[] from = message.getFrom();
	
			//_log.info("from: count: " + from.length);
	
			//_log.info("from: " + from[0]);
	
			Address sender = message.getSender();
	
			//_log.info("sender: " + sender);
	
			printParts(message);
			
			handle(replyTo,session,subject,from);
	

	
		}
		catch (Exception ex) {
			_log.log(Level.WARNING, "Failure in receiving email : " +
					ex.getMessage());
		}
	}
	
	private static void printParts(Part p) throws IOException, MessagingException {
		Object o = p.getContent();
		if (o instanceof String) { 
			_log.info("This is a String: " + (String)o); 
		}
		else if (o instanceof Multipart) {
		
			_log.info("This is a Multipart:");
			Multipart mp = (Multipart)o;
			int count = mp.getCount();
			for(int i = 0; i < count; i++) { 
				printParts(mp.getBodyPart(i));
			}
		}
		else if (o instanceof InputStream) { 
			_log.info("This is an input stream: " + IOUtils.toString((InputStream)o)); 
		
		}

	}
	
	private void handle(
			final Address[] replyTo,
			final Session session,
			final String subject,
			final Address[] from
			) throws AddressException, MessagingException {

		// send response
		// send mail back
		for (Address each : replyTo) {
	
			_log.info("address: " + each);
	
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("happyhour@happyhourplanner.appspotmail.com"));

	
			String[] parts = each.toString().split("[<>]");
	
			for (int i=0; i < parts.length; i++) {
				_log.info("i: " + i + ", value: " + parts[i]);
			}
	
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(parts[1]));
			msg.setSubject("HappyHourPlanner says hi!");
			msg.setText("Thanks for the email.  I've processed it!");
			Transport.send(msg);
	
		}
		
		
	}
}

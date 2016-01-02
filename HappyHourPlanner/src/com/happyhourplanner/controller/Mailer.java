package com.happyhourplanner.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import com.google.apphosting.utils.config.ClientDeployYamlMaker.Request;

public class Mailer {
	
	public static final Logger _log = Logger.getLogger(Mailer.class.getName());
	
	public static void mail(
			final ServletContext servletContext,
			final String fromAddress,
			final String fromName,
			final String toAddress,
			final String subject, 
			final String textBodyFile,
			final String htmlBodyFile,
			final Map<String,String> propertyMap
			) throws AddressException, MessagingException, 
			IOException {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null); 
		Message msg = new MimeMessage(session);
		
		String textBody = FileRetriever.getContent(servletContext, textBodyFile);
		String htmlBody = (htmlBodyFile == null) ? "" : FileRetriever.getContent(servletContext, htmlBodyFile); 
		
		for (String key : propertyMap.keySet()) {
			textBody = textBody.replaceAll("\\$\\{"+key+"}", propertyMap.get(key));
			htmlBody = htmlBody.replaceAll("\\$\\{"+key+"}", propertyMap.get(key));
		}
		
		//_log.info("textBody: " + textBody);
		//_log.info("htmlBody: " + htmlBody);
		
		
		Multipart multipart = new MimeMultipart();
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(textBody,"text/plain");
		multipart.addBodyPart(textPart);
		
		if (!htmlBody.isEmpty()) {
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlBody,"text/html");
			multipart.addBodyPart(htmlPart);
		}
		
		msg.setContent(multipart);
		
		//msg.setText(textBody);
		
		msg.setFrom(new InternetAddress(fromAddress,fromName));
		
		msg.addRecipient(Message.RecipientType.TO,
			new InternetAddress(toAddress));
		msg.setSubject(subject);
		Transport.send(msg);
		
	}
	
	public static void mail(
			final String fromAddress,
			final String fromName,
			final String toAddress,
			final String subject, 
			final String text,
			final String html,
			final Map<String,String> propertyMap
			) throws AddressException, MessagingException, 
			IOException {
		
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null); 
		Message msg = new MimeMessage(session);
		
		String textBody = text;
		String htmlBody = html;
		for (String key : propertyMap.keySet()) {
			textBody = textBody.replaceAll("\\$\\{"+key+"}", propertyMap.get(key));
			htmlBody = htmlBody.replaceAll("\\$\\{"+key+"}", propertyMap.get(key));
		}
		
		//_log.info("textBody: " + textBody);
		//_log.info("htmlBody: " + htmlBody);
		
		
		Multipart multipart = new MimeMultipart();
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(textBody,"text/plain");
		multipart.addBodyPart(textPart);
		
		if (!htmlBody.isEmpty()) {
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlBody,"text/html");
			multipart.addBodyPart(htmlPart);
		}
		
		msg.setContent(multipart);
		
		//msg.setText(textBody);
		
		msg.setFrom(new InternetAddress(fromAddress,fromName));
		
		msg.addRecipient(Message.RecipientType.TO,
			new InternetAddress(toAddress));
		msg.setSubject(subject);
		Transport.send(msg);
		
	}
	

}

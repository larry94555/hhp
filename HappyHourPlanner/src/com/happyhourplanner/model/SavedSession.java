package com.happyhourplanner.model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;


@Entity(name = "SavedSession")
public class SavedSession {
	
	@Id
	private String userName;
	private String sessionId;
	@ElementCollection
	private Map<String,String> sessionAttributes;	
	
	public SavedSession(final HttpSession session, final String userName) {		
		//this.session=session;
		sessionAttributes = new HashMap<String,String>();
		setSessionAttributes(session);
		
		
		this.userName=userName;
		//byte[] salt = Passwords.getNextSalt();
		//sessionId = Passwords.asciiHash(username.toCharArray(),salt);
		this.sessionId = (new Date()).getTime()+"_"+ session.getId();
	}
	
	public Map getSessionAttributes() { return sessionAttributes; }
	
	public void setSessionAttributes(HttpSession session) {
		Enumeration<?> e = session.getAttributeNames();
		while (e.hasMoreElements())
		{
		    String name = (String) e.nextElement();

		    // Get the value of the attribute
		    Object value = session.getAttribute(name);

		    if (value instanceof String) {
		        sessionAttributes.put(name,(String)value);
		    }
		}
	}
	
	public String getSessionId() { 
		return sessionId; 
	}
	


}

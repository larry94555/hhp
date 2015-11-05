package com.happyhourplanner.model;

public class Contact {

	private String emailAddress;
	
	private String name;
	
	
	public Contact(final String emailAddress, final String name) {
		this.emailAddress = emailAddress;
		this.name = name;
	}
	
	public String getName() { return name; }
	public String getEmailAddress() { return emailAddress; }
}

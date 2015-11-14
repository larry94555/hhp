package com.happyhourplanner.model;

public class Contact {

	private String email;
	
	private String name;
	
	
	public Contact(final String email, final String name) {
		this.email = email;
		this.name = name;
	}
	
	public Contact() {
		this.email = "";
		this.name = "";
	}
	
	public String getName() { return name; }
	public String getEmail() { return email; }
}

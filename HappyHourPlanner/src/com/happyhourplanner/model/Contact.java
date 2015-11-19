package com.happyhourplanner.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;


@Entity(name = "Contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String userEmail;
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
	
	public String getUserEmail() { return userEmail; }
	public String getName() { return name; }
	public String getEmail() { return email; }
	public Key getKey() { return key; }
	
	
	public void setUser(final User user) {
		this.userEmail = user.getUserName();
	}
	
}

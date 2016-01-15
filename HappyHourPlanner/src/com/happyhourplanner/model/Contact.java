package com.happyhourplanner.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
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
	
	private String userName;
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
	
	public Contact(final String email) {
		this.email = email;
		String[] parts = email.split("@");
		this.name = parts[0];
	}
	
	public String getUserName() { return userName; }
	public String getName() { return name; }
	public String getEmail() { return email; }
	public Key getKey() { return key; }
	

	public void setUser(final User user) {
		this.userName = user.getUserName();
	}
	
}

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
	
	@ElementCollection
	private Set<Integer> alreadySent;
	
	
	public Contact(final String email, final String name) {
		this.email = email;
		this.name = name;
	}
	
	public Contact() {
		this.email = "";
		this.name = "";
		this.alreadySent = new HashSet<Integer>();
	}
	
	public Contact(final String email) {
		this.email = email;
		String[] parts = email.split("@");
		this.name = parts[0];
		this.alreadySent = new HashSet<Integer>();
	}
	
	public String getUserName() { return userName; }
	public String getName() { return name; }
	public String getEmail() { return email; }
	public Key getKey() { return key; }
	
	public boolean hasInviteInstanceId(final int inviteInstanceId) {
		return alreadySent.contains(inviteInstanceId);
	}
	
	public void addInviteInstanceId(final int inviteInstanceId) {
		alreadySent.add(inviteInstanceId);
	}
	
	public Set<Integer> getAlreadySent() {
		return alreadySent;
	}
	
	public void setAlreadySent(Set<Integer> alreadySent) {
		if (this.alreadySent == null) {
			this.alreadySent = new HashSet<Integer>();
		}
		else {
			this.alreadySent.clear();
		}
		this.alreadySent.addAll(alreadySent);
	}
	
	
	public void setUser(final User user) {
		this.userName = user.getUserName();
	}
	
}

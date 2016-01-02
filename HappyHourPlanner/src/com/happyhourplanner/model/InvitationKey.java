package com.happyhourplanner.model;

import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.happyhourplanner.common.Util;


@Entity(name = "InvitationKey")
public class InvitationKey {
	
public static final Logger _log = Logger.getLogger(InvitationKey.class.getName());
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String username;  // username of the person sending the invite
	private String email; // email address of person receiving invite
	private int invitationInstanceId; // instance of the invite (since invitations repeat)
	
	private String invitationKey;

	public InvitationKey() {
	}
	
	public InvitationKey(final String username, final String email, final int invitationInstanceId) {
		this.username = username;
		this.email = email;
		this.invitationInstanceId = invitationInstanceId;
		this.invitationKey = Util.generateInvitationKey();
	}
	
	public String getInvitationKey() { 
		// return key in safe form
		return invitationKey;
	}
	
	public String getUserName() { return username; }
	
	public String getEmail() { return email; }
	
	public int getInvitationInstanceId() { return invitationInstanceId; }
	
	public void setUserName(final String username) { this.username = username; }
	
	public void setEmail(final String email) { this.email = email; }
	
	public void setInvitationInstanceId(final int invitationInstanceId) { this.invitationInstanceId = invitationInstanceId; }
		

}

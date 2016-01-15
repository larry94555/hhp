package com.happyhourplanner.model;

import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;


@Entity(name = "InvitationKey")
public class InvitationKey {
	
public static final Logger _log = Logger.getLogger(InvitationKey.class.getName());
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String userName;  // username of the person sending the invite
	private String email; // email address of person receiving invite
	private int invitationInstanceId; // instance of the invite (since invitations repeat)
	private int state; // current state of the invitationkey
	
	private String invitationKey;

	public InvitationKey() {
		this.state = Constant.STATE_INVITE_UNSENT;
	}
	
	public InvitationKey(final String userName, final String email, final int invitationInstanceId) {
		this.userName = userName;
		this.email = email;
		this.invitationInstanceId = invitationInstanceId;
		this.invitationKey = Util.generateInvitationKey();
		this.state = Constant.STATE_INVITE_UNSENT;
	}
	
	public String getInvitationKey() { 
		// return key in safe form
		return invitationKey;
	}
	
	public int getState() { return state; }
	
	public String getUserName() { return userName; }
	
	public String getEmail() { return email; }
	
	public int getInvitationInstanceId() { return invitationInstanceId; }
	
	public void setState(final int state) { this.state = state; }
	
	public void setUserName(final String userName) { this.userName = userName; }
	
	public void setEmail(final String email) { this.email = email; }
	
	public void setInvitationInstanceId(final int invitationInstanceId) { this.invitationInstanceId = invitationInstanceId; }
		

}

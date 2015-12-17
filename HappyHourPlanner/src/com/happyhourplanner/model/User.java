package com.happyhourplanner.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.controller.PlaceListServlet;

@Entity(name = "User")
public class User {
	
	public static final Logger _log = Logger.getLogger(User.class.getName());
	
	@Id
	private String emailAddress;
	
	private String name;
	
	private byte[] salt;
	
	private byte[] hash;
	
	private boolean verified;
	
	private boolean disabled;
	
	private int currentState;
	
	private boolean firstTimeUser;
	
	private boolean changePassword;
	
	private String activationCode;
	
	private String passwordResetCode;
	
	private String defaultLocation;
	
	private boolean emailTextOnly; // for testing purposes only
	
	@ElementCollection
	private Map<String,PlaceMarker> placeMarkers;
	
	@Embedded
	private Preferences prefs;
	
	public byte[] getSalt() { return salt; }
	
	public byte[] getHash() { return hash; }
	
	public String getUserName() { return emailAddress; }
	
	private final static Preferences DEFAULT_PREFS = new Preferences(); 
	
	public User(String emailAddress, String password) {
		this.emailAddress = emailAddress;
		this.salt = Passwords.getNextSalt();
		this.hash = Passwords.hash(password.toCharArray(), salt);
		this.verified = false;
		this.disabled = false;
		this.firstTimeUser = true;
		this.changePassword = false;
		this.currentState = 0;
		this.activationCode="";
		this.passwordResetCode="";
		this.emailTextOnly = false;
		this.defaultLocation = Constant.NO_DEFAULT_LOCATION_SET;
		this.placeMarkers = null;
		this.prefs = null;
	}
	
	public boolean isDisabled() { return disabled; }
	
	public boolean isVerified() { return verified; }
	
	public boolean needToChangePwd() { return changePassword; }
	
	public boolean isFirstTime() { return firstTimeUser; }
	
	public boolean emailTextOnly() { return this.emailTextOnly; }
	
	public Map<String,PlaceMarker> getPlaceMarkers() { 
		//return this.placeMarkers;
		return new HashMap<String,PlaceMarker>();
	}
	
	public Preferences getPreferences() { 
	
		if (prefs == null) {
			_log.info("Preferences is null: " + DEFAULT_PREFS.debug());
			return DEFAULT_PREFS;
		}
		else {
			_log.info("Preferences is not null: " + prefs.debug());
			return this.prefs;
		}
	}
	
	public String getDefaultLocation() { 
		return (this.defaultLocation != null) ? this.defaultLocation : Constant.NO_DEFAULT_LOCATION_SET; 
	}
	
	public void setVerifiedFlag(final boolean verified) {
		this.verified=verified;
	}
	
	public void setDisabledFlag(final boolean disabled) {
		this.disabled=disabled;
	}
	
	public void setChangePassword(final boolean changeFlag) {
		this.changePassword = changeFlag;
	}
	
	public void setFirstTimeUser(final boolean firstTimeFlag) {
		this.firstTimeUser=firstTimeFlag;
	}
	
	public void setEmailTextOnly(final boolean flag) {
		this.emailTextOnly = flag;
	}
	
	public void setDefaultLocation(final String location) {
		this.defaultLocation = location;
	}
	
	public boolean checkPassword(final String password) {
		return Passwords.isExpectedPassword(password.toCharArray(), getSalt(), getHash());
	}
	
	public String getActivationCode() {
		return activationCode;
	}
	
	public void setActivationCode(final String activationCode) {
		this.activationCode = activationCode;
	}
	
	public String getPasswordResetCode() {
		return passwordResetCode;
	}
	
	public void setPasswordResetCode(final String passwordResetCode) {
		this.passwordResetCode = passwordResetCode;
	}
	
	public void setPlaceMarkers(final Map<String,PlaceMarker> placeMarkers) {
		this.placeMarkers = placeMarkers;
	}
	
	public void setPreferences(final Preferences prefs) {
		this.prefs = prefs;
	}
	
	public int getCurrentState() { return currentState; }
	
	public String getCurrentStateAsString() {
		switch(currentState) {
		case Constant.STATE_VERIFY_EMAIL_NUM: return Constant.STATE_VERIFY_EMAIL;
		case Constant.STATE_CONTACT_LIST_NUM: return Constant.STATE_CONTACT_LIST;
		case Constant.STATE_PREFERENCES_NUM: return Constant.STATE_PREFERENCES;
		case Constant.STATE_INVITE_NUM: return Constant.STATE_INVITE;
		case Constant.STATE_STATUS_NUM: return Constant.STATE_STATUS;
		case Constant.STATE_DATE_SET_NUM: return Constant.STATE_DATE_SET;
		default: return Constant.STATE_UNDEFINED;
		}
	}
	
	public void setCurrentState(final int newState) {
		this.currentState = newState;
	}
	
}

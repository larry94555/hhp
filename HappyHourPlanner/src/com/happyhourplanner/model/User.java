package com.happyhourplanner.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.happyhourplanner.common.Constant;

@Entity(name = "User")
public class User {
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
	
	private boolean emailTextOnly; // for testing purposes only
	
	public byte[] getSalt() { return salt; }
	
	public byte[] getHash() { return hash; }
	
	public String getUserName() { return emailAddress; }
	
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
	}
	
	public boolean isDisabled() { return disabled; }
	
	public boolean isVerified() { return verified; }
	
	public boolean needToChangePwd() { return changePassword; }
	
	public boolean isFirstTime() { return firstTimeUser; }
	
	public boolean emailTextOnly() { return this.emailTextOnly; }
	
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

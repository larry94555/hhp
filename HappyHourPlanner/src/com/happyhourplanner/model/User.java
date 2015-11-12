package com.happyhourplanner.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	
	@ElementCollection
	private Set<String> contacts;	
	
	
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
		this.contacts = new HashSet<String>();
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
		case 0: return "verify email";
		case 1: return "contact list";
		case 2: return "preferences";
		case 3: return "invite";
		case 4: return "status";
		case 5: return "date";
		default: return "undefined";
		}
	}
	
	public void setCurrentState(final int newState) {
		this.currentState = newState;
	}
	
	private Set<String> getContactsAsString() { return contacts; }
	
	public List<Contact> getContacts() {
		List<Contact> list = new ArrayList<Contact>();
		for (String contact : contacts) {
			String[] parts = contact.split("@@");
			list.add(new Contact(parts[Constant.EMAIL_PART],parts[Constant.NAME_PART]));
		}
		return list;
	}
	
	
	private void addContact(final String contact) {
		if (!contacts.contains(contact)) {
			contacts.add(contact);
		}
	}
	
	public void addContact(final String email,final String name) {
		contacts.add(new StringBuilder(email).append("@@").append(name).toString());
	}
}

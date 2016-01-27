package com.happyhourplanner.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

public class InviteGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String userName;  // user
	private String groupName;
	private int groupId;
	private int currentInstance;
	
	public String getUserName() { return userName; }
	public String getGroupName() { return groupName; }
	public int getGroupId() { return groupId; }
	public int getCurrentInstance() { return currentInstance; }
	
	public void setUserName(final String userName) { this.userName = userName; }
	public void setGroupName(final String groupName) { this.groupName = groupName; }

}

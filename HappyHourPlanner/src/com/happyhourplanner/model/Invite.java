package com.happyhourplanner.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.ServletContext;

import com.google.appengine.api.datastore.Key;
import com.happyhourplanner.common.Constant;
import com.happyhourplanner.controller.FileRetriever;

@Entity(name = "Invite")
public class Invite {
	
	public static final Logger _log = Logger.getLogger(Invite.class.getName());
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	private String username;  // user
	private String groupName;
	private int groupId;
	
	private String subject;
	
	private int inviteInstanceId; // generated new for each invite instance
	
	private String text;
	private String html;

	@ElementCollection
	private List<String> invitees; // string specifying contact
	
	public Invite() {
		//this.text = FileRetriever.getContent(servletContext, textBodyFile);Constant.DEFAULT_INVITE_TEXT_FILE;
		//this.html = Constant.DEFAULT_INVITE_HTML_FILE;
	}
	
	public Invite(
			final String username,
			final String groupName,
			final String subject,
			final List<String> invitees,
			final String text,
			final String html,
			final int groupId,
			final int inviteInstanceId
			) throws IOException {
		
		this.username = username;
		this.groupName = groupName;
		this.subject = subject;
		
		this.text = text;
		this.html = html;
		this.groupId = groupId;
		
		if (invitees != null && this.invitees != null) {
			this.invitees.clear();
			this.invitees.addAll(invitees);	
		}
		
		this.inviteInstanceId = inviteInstanceId;
		
		
		
	}
	
	public String getUsername() { return username; }
	
	public String getGroupName() { return groupName; }
	
	public String getSubject() {
		if (subject == null) {
			return Constant.DEFAULT_SUBJECT;
		}
		else {
			return subject;
		}
	}
	
	public int getInviteInstanceId() { return inviteInstanceId; }
	
	public List<String> getInvitees() {
		List<String> copyInvitees = new ArrayList<String>();
		copyInvitees.addAll(invitees);
		return copyInvitees;
	}
	
	public String getInviteesAsString() {
		if (invitees == null || invitees.isEmpty()) return "";
		
		StringBuilder builder = new StringBuilder("[");
		for (String invitee : invitees) {
			
			if (builder.length() > 1) {
				builder.append(",");
			}
			
			builder.append("\"")
				.append(invitee)
				.append("\"");
		}
		builder.append("]");
		
		return builder.toString();
	}
	
	public String getText() { return text; }
	
	public String getHtml() { return html; }
	
	public int getGroupId() { return groupId; }
	
	public void setUsername(final String username) { this.username = username; }
	
	public void setGroupName(final String groupName) { this.groupName = groupName; }
	
	public void setGroupId(final int id) { this.groupId = id; }
	
	public void setSubject(final String subject) { this.subject = subject; }
	
	public void setInvitees(final List<String> invitees) {
		this.invitees.clear();
		this.invitees.addAll(invitees);
	}
	
	public void setInviteInstanceId(final int inviteInstanceId) { this.inviteInstanceId = inviteInstanceId; }
	
	public void setText(final String text) { this.text = text; }
	
	public void setHtml(final String html) { this.html = html; }
		

}

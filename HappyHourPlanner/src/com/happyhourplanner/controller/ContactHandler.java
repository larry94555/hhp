package com.happyhourplanner.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.Query;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.Contact;
import com.happyhourplanner.model.EM;
import com.happyhourplanner.model.InvitationKey;
import com.happyhourplanner.model.User;

public class ContactHandler {
	
	private static final Logger _log = Logger.getLogger(UserAccountHandler.class.getName());
	
	public static Contact find(final User user, final String email) {
		
		Query query = EM.get().createQuery("SELECT c FROM Contact c WHERE c.userName = :userName and c.email = :email");
		query.setParameter("userName", user.getUserName());
		query.setParameter("email",email);
		query.setFirstResult(0);
		query.setMaxResults(2);
				
		if (query.getResultList().isEmpty()) {
			return null;
		}
		else {
			return (Contact)query.getResultList().get(0);
		}
		
	}
	
	public static InvitationKey find(final User user, final String email, final int invitationInstanceId) {
		Query query = EM.get().createQuery("SELECT i FROM InvitationKey i WHERE i.userName = :userName and i.email = :email and i.invitationInstanceId = :invitationInstanceId");
		query.setParameter("userName", user.getUserName());
		query.setParameter("email",email);
		query.setParameter("invitationInstanceId",invitationInstanceId);
		query.setFirstResult(0);
		query.setMaxResults(1);
				
		if (query.getResultList().isEmpty()) {
			
			// Create new invitation key
			InvitationKey invitationKey = new InvitationKey(user.getUserName(),email,invitationInstanceId);
			EM.get().persist(invitationKey);
			EM.commit();
			return invitationKey;
			
		}
		else {
			return (InvitationKey)query.getResultList().get(0);
		}
	}
	
	public static void remove(final User user, final String email) {
		if (user != null) {
			Contact contact = find(user,email);
			if (contact != null) {
				EM.get().remove(contact);
				EM.commit();
			}
		}
	}
	
	public static String getInvitationKey(
			final User user,
			final Contact contact,
			final String email,
			final int inviteInstanceId) {
		
		// check if invitation key exists
		InvitationKey invitationKey = find(user,email,inviteInstanceId);
		return invitationKey.getInvitationKey();
	}
	
	public static List<String> sendToNewUsersOnly(
			final User user,
			final String text,
			final String html,
			final String subject,
			final int inviteInstanceId,
			final String[] toList) throws AddressException, IOException, MessagingException {
		
		List<String> list = new ArrayList<String>();
		
		final Map<String,String> propertyMap = new HashMap<String,String>();
    	
		
		for (String email : toList) {
			
			Contact contact = find(user,email);
			if (contact != null) {
				if (!contact.hasInviteInstanceId(inviteInstanceId)) {
					
					propertyMap.put(Constant.INVITATION_KEY,getInvitationKey(
							user,
							contact,
							email,
							inviteInstanceId));
					
					// send Invite
					Mailer.mail(
							Constant.FROM_ADDRESS, 
							Constant.FROM_NAME, 
							"larry.freeman@gmail.com",
							subject, 
							text, 
							html, 
							propertyMap);
					
					contact.addInviteInstanceId(inviteInstanceId);
					list.add(email);
				}
			}
			
		}
		
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Contact> getContacts(final User user,final int offset,final int count) {
		
		Query query = EM.get().createQuery(
				"SELECT c FROM Contact c where c.userName = :userName");
		query.setParameter("userName", user.getUserName());
		query.setFirstResult(offset);
		query.setMaxResults(count);
		
		// to do: check why these logs appear so often.
		//_log.info("userEmail = " + user.getUserName() + ", offset = " + offset + ", count = " + count);
		
		//_log.info("Found: " + query.getResultList().size() + " items.");
//		for (Contact contact : (List<Contact>)query.getResultList()) {
//			_log.info("contact email = " + contact.getEmail());
//		}
		return (List<Contact>)query.getResultList();
		//return new ArrayList<Contact>();
			
	}
	
	public static void addContactList(final User user,final Contact[] contacts) {
		
		if (user != null) {
			for (Contact contact : contacts) {
				if (find(user,contact.getEmail()) == null) {
					//_log.info("contact does not yet exist!");
					contact.setUser(user);
					EM.get().persist(contact);
					EM.commit();
				}
				else {
					//_log.info("contact exists");
				}
			}
	
		}
		
	}
	
	public static void addContactList(final User user,final String[] emails) {
		if (user != null) {
			for (String email : emails) {
				Contact contact = find(user,email);
				if (contact == null) {
					contact = new Contact(email);
					EM.get().persist(contact);
					EM.commit();
				}
			}
		}
	}
	
	

}

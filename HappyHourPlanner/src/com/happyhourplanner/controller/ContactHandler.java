package com.happyhourplanner.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;

import com.happyhourplanner.model.Contact;
import com.happyhourplanner.model.EM;
import com.happyhourplanner.model.User;

public class ContactHandler {
	
	private static final Logger _log = Logger.getLogger(UserAccountHandler.class.getName());
	
	public static Contact find(final User user, final String email) {
		
		Query query = EM.get().createQuery("SELECT c FROM Contact c WHERE c.userEmail = :userEmail and c.email = :email");
		query.setParameter("userEmail", user.getUserName());
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
	
	public static void remove(final User user, final String email) {
		if (user != null) {
			Contact contact = find(user,email);
			if (contact != null) {
				EM.get().remove(contact);
				EM.commit();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Contact> getContacts(final User user,final int offset,final int count) {
		
		Query query = EM.get().createQuery(
				"SELECT c FROM Contact c where c.userEmail = :userEmail");
		query.setParameter("userEmail", user.getUserName());
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
					_log.info("contact does not yet exist!");
					contact.setUser(user);
					EM.get().persist(contact);
					EM.commit();
				}
				else {
					_log.info("contact exists");
				}
			}
	
		}
		
	}
	
	

}

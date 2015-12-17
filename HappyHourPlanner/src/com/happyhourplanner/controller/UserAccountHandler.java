package com.happyhourplanner.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServletRequest;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.Contact;
import com.happyhourplanner.model.EM;
import com.happyhourplanner.model.EMF;
import com.happyhourplanner.model.Passwords;
import com.happyhourplanner.model.PlaceMarker;
import com.happyhourplanner.model.Preferences;
import com.happyhourplanner.model.ResponseBean;
import com.happyhourplanner.model.SavedSession;
import com.happyhourplanner.model.User;

public class UserAccountHandler {
	
	public static final Logger _log = Logger.getLogger(UserAccountHandler.class.getName());
	
	private static final String VALID_EMAIL = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	
	public static boolean checkIfValidEmailAddress(final String username) {
		return username.matches(VALID_EMAIL);
	}
	
	public static boolean exists(final String username) {
		return EM.get().find(User.class,username) != null;
	}
	
	public static String getActivationKey(final String username) {
		User user = find(username);
		user.setActivationCode(Util.generateActivationKey());
		persist(user);
		return user.getActivationCode();
	}
	
	public static String getPasswordResetKey(final String username) {
		User user = find(username);
		user.setPasswordResetCode(Util.generateActivationKey());
		persist(user);
		return user.getPasswordResetCode();
	}
	
	public static User find(final String username) {
		
		return EM.get().find(User.class, username);
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers(int offset,int count) {
		
		Query query = EM.get().createQuery(
				"SELECT u FROM User u");
		query.setFirstResult(offset);
		query.setMaxResults(count);
		return (List<User>) query.getResultList();
		
		
	}
	
	public static int getUserCount() {
		CriteriaBuilder qb = EM.get().getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(User.class)));
		return EM.get().createQuery(cq).getSingleResult().intValue();		
		
	}
	
	private static User getUserByActivationCode(final String activationCode) {
		
		Query query = EM.get().createQuery("SELECT u FROM User u WHERE u.activationCode = :activationCode");
		query.setParameter("activationCode", activationCode);
		query.setFirstResult(0);
		query.setMaxResults(2);
				
		@SuppressWarnings("unchecked")
		List<User> results = (List<User>)query.getResultList();
		
		_log.info("Found: " + results.size() + " result(s).");
		
		if (results.size() == 1) {
			return results.get(0);
		}
		
		
		return null;
	}
	
	private static User getUserByPasswordResetCode(final String passwordResetCode) {
		Query query = EM.get().createQuery("SELECT u FROM User u WHERE u.passwordResetCode = :passwordResetCode");
		query.setParameter("passwordResetCode", passwordResetCode);
		query.setFirstResult(0);
		query.setMaxResults(2);
				
		@SuppressWarnings("unchecked")
		List<User> results = (List<User>)query.getResultList();
		
		_log.info("Found: " + results.size() + " result(s).");
		
		if (results.size() == 1) {
			return results.get(0);
		}
		
		
		return null;
	}
	
	public static void activateUser(final String activationCode,User user) {
		
		if (user == null) {
			// in this case, retrieve use using activation code
			user = getUserByActivationCode(activationCode);
		}
		
		// if user context, then check user and persist
		if (user != null && !user.isVerified()) {
			if (user.getActivationCode().equals(activationCode)) {				
				user.setVerifiedFlag(true);
				user.setCurrentState(1);
				persist(user);
				
			}

		}
		
		// else, do nothing.
		
	}
	
	public static boolean resetPassword(final String passwordResetCode,User user) {
		if (user == null) {
			user = getUserByPasswordResetCode(passwordResetCode);
		}
		
		if (user != null && user.getPasswordResetCode().equals(passwordResetCode)) {
			return true;
		}
		
		return false;
	}

	
	public static boolean login(final String username,final String password) {
		User user = find(username);
		if (user == null) return false;
		if (!user.isVerified()) return false;
		if (user.isDisabled()) return false;
		
		if (user.checkPassword(password)) {
			
			return true;
		}
		return false;
	}
	
	public static boolean isDisabled(final String username) {
		User user = find(username);
		return (user.isDisabled());
	}
	
	public static boolean isVerified(final String username) {
		User user = find(username);
		return (user.isVerified());
	}
	
	public static boolean signup(final String username,final String password) {
		
		User user = new User(username,password);
		
		persist(user);
		
		//_log.info("Persisted: username: " + user.getUserName());
			
		return true;
	}
	
	public static void setCurrentState(final User user,final int newState) {
		user.setCurrentState(newState);
		persist(user);
	}
	
	private static void persist(final User user) {
		EM.get().persist(user);
		EM.commit();
	}
	
	public static User validateUser(final String username, final PrintWriter out) {
		
		// verify that email address is valid
		if (!UserAccountHandler.checkIfValidEmailAddress(username)) {
	    	ResponseBean.println(out,Constant.EMAIL_ADDRESS_NOT_VALID);
	    	return null;
	    }
		
		if (!UserAccountHandler.exists(username)) {
			ResponseBean.println(out, Constant.ACCOUNT_NOT_EXIST);
			return null;
		}
		
		final User user = UserAccountHandler.find(username);
		if (user.isDisabled()) {
    		ResponseBean.println(out, Constant.ACCOUNT_DISABLED);
    		return null;
    	}
		
		if (!user.isVerified()) {
    		ResponseBean.println(out, Constant.NEED_TO_VERIFY);
    		return null;
    	}
		
		
		return user;
	}
	
	public static String generateContactListHtml(final User user) {
		StringBuilder html = new StringBuilder("");
		
		// build the new contact list and send it out.
		for (String a : Util.getAlphabet()) {
			Map<String,Contact> contactMap = getContactSubList(user,a);
			if (contactMap.size() > 0) {
				html.append("<li id='")
					.append(a.toLowerCase())
					.append("'><a name='")
					.append(a.toLowerCase())
					.append("' class='title'>")
					.append(a.toUpperCase())
					.append("</a>")
		        	.append("<ul>");
				
				for (String email : contactMap.keySet()) { 

					final String name = contactMap.get(email).getName();
					
					html.append("<li>")
						.append("<a href='#' class='contact-list-entry'><span class='glyphicon glyphicon-remove-circle remove-indicator'></span> ")
						.append(name)
						.append("</a>")
						.append("<span class='contact-list-email'>")
						.append(email)
						.append("</span>")
						.append("</li>");
						
				
				}
				
				html.append("</ul></li>");
				
			}
			
		}
		
		return html.toString();
	}
	

	
	public static Map<String,Contact> getContactSubList(final User user,final String letter) {
		
		Map<String,Contact> map= new TreeMap<String,Contact>();
		
		if (user != null) {
		
			for (Contact contact : ContactHandler.getContacts(user,0,Constant.MAX_CONTACTS)) {
			
				
				if (contact.getName().toUpperCase().startsWith(letter)) {
					map.put(contact.getEmail(),contact);
				}
				
			}
		
		}
		
		return map;
		
	}
	
	public static void updatePreferences(final User user,Map<String,String> map, Preferences preferences) {
		user.setPlaceMarkerMap(map);
		user.setPreferences(preferences);
		if (user.getCurrentState() < Constant.STATE_INVITE_NUM) {
			user.setCurrentState(Constant.STATE_INVITE_NUM);
		}
		persist(user);
		
	}
	


}

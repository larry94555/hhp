package com.happyhourplanner.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.happyhourplanner.common.Util;
import com.happyhourplanner.model.EM;
import com.happyhourplanner.model.EMF;
import com.happyhourplanner.model.Passwords;
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
		user.setActivationKey(Util.generateActivationKey());
		persist(user);
		return user.getActivationKey();
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
	
	private static void persist(final User user) {
		EM.get().persist(user);
		EM.commit();
	}

}

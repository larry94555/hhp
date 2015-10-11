package com.happyhourplanner.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.model.EM;
import com.happyhourplanner.model.SavedSession;

public class SavedSessionHandler {
	
	public static final Logger _log = Logger.getLogger(SavedSessionHandler.class.getName());
	
	public static Map getSessionAttributes(final String sessionIdStr) {
		
		_log.info("SavedSessionHandler:getSession: sessionIdStr = " + sessionIdStr);
		
		//final SavedSession savedSession = EM.get().find(SavedSession.class, sessionId);
		// First, check if session already exists for this user.
		Query query = EM.get().createQuery("SELECT s FROM SavedSession s WHERE s.sessionId = :sessionId");
		query.setParameter("sessionId", sessionIdStr);
		query.setFirstResult(0);
		query.setMaxResults(1);
				
		@SuppressWarnings("unchecked")
		List<SavedSession> results = (List<SavedSession>)query.getResultList();
		
		_log.info("Found: " + results.size() + " result(s).");
		
		if (results.size() == 1) {
			if (results.get(0).getSessionAttributes() == null) {
				_log.info("Session is null");
				return Constant.EMPTY_MAP;
			}
			_log.info("Username = " + results.get(0).getSessionAttributes().get(Constant.USERNAME));
			return results.get(0).getSessionAttributes();
		}
		
			
		return Constant.EMPTY_MAP;
	}
	
	public static String saveSession(final HttpSession session,final String username) {
		
		_log.info("SavedSessionHandler.savedSession: entering: username = " + username);
		
		SavedSession savedSession = EM.get().find(SavedSession.class, username);
		
		
		
		if (savedSession != null) {
			savedSession.setSessionAttributes(session);
			persist(savedSession);
			return savedSession.getSessionId();
		}
		else {
			_log.info("username not found -- persisting for first time");
		}
		
		// generate sessionId
		savedSession = new SavedSession(session,username);
		persist(savedSession);
		return savedSession.getSessionId();
	}
	
	private static void persist(final SavedSession savedSession) {
		EM.get().persist(savedSession);
		EM.commit();
	}

}


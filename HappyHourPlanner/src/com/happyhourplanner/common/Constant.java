package com.happyhourplanner.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	// Email
	public static final String FROM_ADDRESS = "happyhour@happyhourplanner.appspotmail.com";
	public static final String FROM_NAME = "Happy Hour Planner";
	
	// Email Properties
	public static final String ACTIVATION_KEY = "activationKey";
	public static final int ACTIVATION_KEY_LENGTH = 40;
	
	public static final String PASSWORD_RESET_KEY = "passwordResetKey";
	public static final int PASSWORD_RESET_KEY_LENGTH = 40;
	
	// Verify Email
	public static final String VERIFY_EMAIL_SUBJECT = "[Happy Hour Planner] Please confirm your email address";
	public static final String RESET_PASSWORD_EMAIL_SUBJECT = "[Happy Hour Planner] A request was made to reset your password";
	
	public static final String VERIFY_EMAIL_TEXT_FILE = "/WEB-INF/resources/verifyEmail.txt";
	public static final String RESET_PASSWORD_EMAIL_TEXT_FILE = "/WEB-INF/resources/resetPasswordEmail.txt";
	
	public static final String STATUS = "currentStatus";
	public static final String BAD_ACTIVATION_CODE = "badActivationCode";
	public static final String BAD_PASSWORD_RESET_CODE = "badPasswordResetCode";
	public static final String JUST_ACTIVATED = "justActiviated";
	public static final String PASSWORD_RESENT = "passwordResent";
	
	public static final String VERIFY_EMAIL_HTML_FILE = "/WEB-INF/resources/verifyEmail.html";
	public static final String RESET_PASSWORD_EMAIL_HTML_FILE = "/WEB-INF/resources/resetPasswordEmail.html";
	
	// Attributes
	public static final Map<String,String> EMPTY_MAP = new HashMap<String,String>();
	
	public static final String USERNAME = "username";
	public static final String COOKIE_NAME = "SESSID";
	public static final int COOKIE_MAX_AGE = 60*60*24*365; // 1 year
	
	// Actions
	public static final String ACTION_SIGNUP = "signup";
	public static final String ACTION_LOGIN = "login";
	
	// Error messages
	
	public static final String ACCOUNT_DISABLED = "Account is disabled.";
	
	public static final String ACCOUNT_EXISTS = "Account already exists.";
	
	public static final String ACCOUNT_NOT_EXIST = "Account doesn't exist.";
	
	public static final String EMAIL_ADDRESS_NOT_VALID = "Email address is not valid.";

	public static final String PASSWORDS_DONT_MATCH = "Passwords don't match.";
	
	public static final String PROBLEM_OCCURRED = "A problem occurred.  Please try again later.";
	
	public static final String NEED_TO_VERIFY = "An email has been sent.  Please click the link to verify your account";
	
	public static final String LINK_SENT_TO_USER = "An email has been sent.";

}

package com.happyhourplanner.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	// Contacts
	public static final int MAX_CONTACTS = 1000;
	
	// Preferences
	public static final String DEFAULT_MIN_AVAILABLE = "3";
	
	// Invites
	public static final String DEFAULT_GROUP_NAME = "Group 1";
	public static final String DEFAULT_SUBJECT = "Would folks be up for a Happy Hour?";
	public static final String DEFAULT_INVITE_TEXT_FILE = "/WEB-INF/resources/defaultInvite.txt";
	public static final String DEFAULT_INVITE_HTML_FILE = "/WEB-INF/resources/defaultInvite.html";
	
	// HTML
	public static final String CHECKED = "checked=\"checked\"";
	public static final String SELECTED = "selected=\"selected\"";
	public static final String EMPTY_STRING = "";
	
	// Yelp Properties
	public static final int YELP_REQUEST_LIMIT = 20;
	public static final String UNKNOWN_RATING = "unknown_rating";
	
	// Email
	public static final String FROM_ADDRESS = "happyhour@happyhourplanner.appspotmail.com";
	public static final String FROM_NAME = "Happy Hour Planner";
	
	// Email Properties
	public static final String ACTIVATION_KEY = "activationKey";
	public static final String INVITATION_KEY = "invitationKey";
	public static final int ACTIVATION_KEY_LENGTH = 40;
	public static final int INVITATION_KEY_LENGTH = 40;
	
	public static final String NAME_PROPERTY_KEY = "name";
	public static final String EMAIL_PROPERTY_KEY = "emailAddress";
	
	public static final String PASSWORD_RESET_KEY = "passwordResetKey";
	public static final int PASSWORD_RESET_KEY_LENGTH = 40;
	
	// Verify Email
	public static final String VERIFY_EMAIL_SUBJECT = "[Happy Hour Planner] Please confirm your email address";
	public static final String RESET_PASSWORD_EMAIL_SUBJECT = "[Happy Hour Planner] A request was made to reset your password";
	
	public static final String VERIFY_EMAIL_TEXT_FILE = "/WEB-INF/resources/testVerifyEmail.txt";
	public static final String RESET_PASSWORD_EMAIL_TEXT_FILE = "/WEB-INF/resources/resetPasswordEmail.txt";
	
	public static final String STATUS = "currentStatus";
	public static final String BAD_ACTIVATION_CODE = "badActivationCode";
	public static final String BAD_PASSWORD_RESET_CODE = "badPasswordResetCode";
	public static final String JUST_ACTIVATED = "justActiviated";
	public static final String PASSWORD_RESENT = "passwordResent";
	
	public static final String VERIFY_EMAIL_HTML_FILE = "/WEB-INF/resources/testVerifyEmail.html";
	//public static final String VERIFY_EMAIL_HTML_FILE = "/WEB-INF/resources/verifyEmail.html";
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
	
	public static final String PROBLEM_OCCURRED = "A problem occurred.";
	
	public static final String NEED_TO_VERIFY = "An email has been sent.";
	
	public static final String LINK_SENT_TO_USER = "An email has been sent.";
	
	public static final String CONTACTS_ADDED = "Contacts added.";
	
	public static final String LOCATION_MISSING = "Location not sent.";
	
	public static final String CATEGORY_MISSING = "Category not sent.";
	
	public static final String NO_DEFAULT_LOCATION_SET = "detect";
	
	public static final String PREFERENCES_SAVED = "Preferences saved.";
	
	public static final String CHANGES_SAVED = "Changes saved.";
	
	// contacts stored as <email>@@<name>
	public static final int NAME_PART=1;
	public static final int EMAIL_PART=0;
	
	// user states
	public static final String STATE_VERIFY_EMAIL = "verify email";
	public static final String STATE_CONTACT_LIST = "contact list";
	public static final String STATE_PREFERENCES = "preferences";
	public static final String STATE_INVITE = "invite";
	public static final String STATE_STATUS = "status";
	public static final String STATE_DATE_SET = "date";
	public static final String STATE_UNDEFINED = "undefined";

	
	public static final int STATE_VERIFY_EMAIL_NUM = 0;
	public static final int STATE_PREFERENCES_NUM = 1;
	public static final int STATE_INVITE_NUM = 2;
	public static final int STATE_CONTACT_LIST_NUM = 3;
	public static final int STATE_STATUS_NUM = 4;
	public static final int STATE_DATE_SET_NUM = 5;
}

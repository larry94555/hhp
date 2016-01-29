<%@ page import="com.happyhourplanner.controller.UserAccountHandler" %>
<%@ page import="com.happyhourplanner.model.User" %>
<%@ page import="com.happyhourplanner.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happyhourplanner.common.Constant" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
	// check for cookie
	final User user = Util.checkForUser(request,response);
	
	final String status = (String)session.getAttribute(Constant.STATUS);
	
	final int currentState =  user.getCurrentState();
%> 
 
 
<div id="pref-container">
<div id="pref-form1">
  
<div id='select-container'>

<div id="preferences-title">Set Preferences</div>

<%

	// handle options
	String prefAllowSuggestions = (user.getPreferences().isAllowSuggestions()) ? Constant.CHECKED : "";
	String prefUseMinRating = (user.getPreferences().isUseMinRating()) ? Constant.CHECKED : "";
	String minRating = user.getPreferences().getMinRating();
	String restaurantsOnly = (user.getPreferences().isRestaurantsOnly()) ? Constant.CHECKED : "";
	String fullBar = (user.getPreferences().isFullBar())? Constant.CHECKED : "";
	String minAvailable = user.getPreferences().getMinAvailable();
	
	String mon = (user.getPreferences().getAvailability().indexOf("Mo") != -1) ? Constant.CHECKED : "";
	String tues = (user.getPreferences().getAvailability().indexOf("Tu") != -1) ? Constant.CHECKED : "";
	String wed = (user.getPreferences().getAvailability().indexOf("We") != -1) ? Constant.CHECKED : "";
	String thur = (user.getPreferences().getAvailability().indexOf("Th") != -1) ? Constant.CHECKED : "";
	String fri = (user.getPreferences().getAvailability().indexOf("Fr") != -1) ? Constant.CHECKED : "";
	String sat = (user.getPreferences().getAvailability().indexOf("Sa") != -1) ? Constant.CHECKED : "";
	String sun = (user.getPreferences().getAvailability().indexOf("Su") != -1) ? Constant.CHECKED : "";
	
%>

<div id="preference-settings">
<div id="allow-suggestions-container">
<input type="checkbox" name="pref-allow-suggestions" class="standard-checkbox pref-setting" id="pref-allow-suggestions" <%= prefAllowSuggestions %> /><label for="pref-allow-suggestions">Let People Suggest Places</label><br /><br />
</div>

<input type="checkbox" name="skip-low-ratings" class="standard-checkbox place-search-option pref-setting" id="skip-low-ratings" <%= prefUseMinRating %> />
<label for="skip-low-ratings">Disallow places rated lower than</label><input type="text" name="min-rating" id="min-rating" class="place-search-option pref-setting" value="<%= minRating %>"></input> stars<br/><br/>

<input type="checkbox" name="pref-restaurants-only" class="standard-checkbox place-search-option pref-setting" id="pref-restaurants-only" <%= restaurantsOnly %>/><label for="pref-restaurants-only">Restaurants only</label><br /><br />

<input type="checkbox" name="pref-spirits-too" class="standard-checkbox place-search-option pref-setting" id="pref-spirits-too" <%= fullBar %> /><label for="pref-spirits-too">Full bar (wine, beer, and spirits)</label><br /><br />

<label for="min-num-available">At least <input type="text" class="pref-setting" id="min-num-available" name="min-num-available" value="<%=minAvailable %>" /> people must accept to schedule.</label><br /><br />

<input type="checkbox" name="pref-allow-m" class="standard-checkbox pref-setting" id="pref-allow-m" <%= mon %> /><label for="pref-allow-m">M</label>
<input type="checkbox" name="pref-allow-t" class="standard-checkbox pref-setting" id="pref-allow-t" <%= tues %> /><label for="pref-allow-t">T</label>
<input type="checkbox" name="pref-allow-w" class="standard-checkbox pref-setting" id="pref-allow-w" <%= wed %> /><label for="pref-allow-w">W</label>
<input type="checkbox" name="pref-allow-th" class="standard-checkbox pref-setting" id="pref-allow-th" <%= thur %> /><label for="pref-allow-th">Th</label>
<input type="checkbox" name="pref-allow-f" class="standard-checkbox pref-setting" id="pref-allow-f" <%= fri %> /><label for="pref-allow-f">F</label>
<input type="checkbox" name="pref-allow-sa" class="standard-checkbox pref-setting" id="pref-allow-sa" <%= sat %> /><label for="pref-allow-sa">Sat</label>
<input type="checkbox" name="pref-allow-su" class="standard-checkbox pref-setting" id="pref-allow-su" <%= sun %> /><label for="pref-allow-su">Sun</label>

</div>

<label for="default-location">Location:</label><span id="def-location">current (<span id="detected-location"></span>)</span><a id="set-default-location">set</a>
<input type="hidden" id="detected-latitude"></input>
<input type="hidden" id="detected-longitude"></input>
<input type="hidden" id="place-search-offset" value="0"></input>
<input type="hidden" id="main-current-state" value="<%= currentState %>"></input>

<div id="override-current-location">
<input type="text" name="pref-near" id="pref-near" class="place-search-option" placeholder="Enter city, neighborhood, zip, or cross streets" />
</div>

<div id='container'> 
<label for="pref-list">Places:</label><br /> 

<div class="loadingText" id="placeListLoadingIndicator">Loading...</div>
<select id="place-list-select" name="character" multiple="multiple"> 
</select>

<a href="http://yelp.com" title="list powered by yelp"><img id='yelp-image' src="/images/yelp_powered_btn_dark.png"></a>
</div>

<div id="preferences-continue-button">
<a href="#" id="accept-preferences" class="special button">Continue</a>
</div>

</div>    
    
    </div>
</div>
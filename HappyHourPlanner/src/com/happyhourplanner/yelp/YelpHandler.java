package com.happyhourplanner.yelp;

import java.text.Normalizer;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.happyhourplanner.common.Constant;
import com.happyhourplanner.controller.PlaceListServlet;
import com.happyhourplanner.key.Secret;
import com.happyhourplanner.key.TwoStepOAuth;
import com.happyhourplanner.key.YelpAPI;
import com.happyhourplanner.model.PlaceMarker;
import com.happyhourplanner.model.User;

public class YelpHandler {
	
	public static final Logger _log = Logger.getLogger(YelpHandler.class.getName());
	
	//private static final int SEARCH_LIMIT =5;
	private static final String SEARCH_PATH = "/v2/search";
	private static final String BUSINESS_PATH = "/v2/business";
	private static final String API_HOST = "api.yelp.com";

	
	
	private OAuthService service;
	private Token accessToken;
	
	public YelpHandler(final String consumerKey, 
			final String consumerSecret,
			final String token,
			final String tokenSecret
			) {
		
		this.service =
		        new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
		            .apiSecret(consumerSecret).build();
		this.accessToken = new Token(token, tokenSecret);
		
		
	}
	
	
	  /**
	   * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
	   * 
	   * @param path API endpoint to be queried
	   * @return <tt>OAuthRequest</tt>
	   */
	  private OAuthRequest createOAuthRequest(String path) {
	    OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
	    return request;
	  }
	  
	  /**
	   * Sends an {@link OAuthRequest} and returns the {@link Response} body.
	   * 
	   * @param request {@link OAuthRequest} corresponding to the API request
	   * @return <tt>String</tt> body of API response
	   */
	  private String sendRequestAndGetResponse(OAuthRequest request) {
	    //System.out.println("Querying " + request.getCompleteUrl() + " ...");
	    this.service.signRequest(this.accessToken, request);
	    Response response = request.send();
	    return response.getBody();
	  }
	  
	  private static String normalize(final String word) {
		  return  Normalizer
			        .normalize(word, Normalizer.Form.NFD)
			        .replaceAll("[^\\p{ASCII}]", "");
	  }
	  
	  /**
	   * Creates and sends a request to the Business API by business ID.
	   * <p>
	   * See <a href="http://www.yelp.com/developers/documentation/v2/business">Yelp Business API V2</a>
	   * for more info.
	   * 
	   * @param businessID <tt>String</tt> business ID of the requested business
	   * @return <tt>String</tt> JSON Response
	   */
	  public String searchByBusinessId(String businessID) {
	    OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
	    return sendRequestAndGetResponse(request);
	  }
	
	  /**
	   * Creates and sends a request to the Search API by term and location.
	   * <p>
	   * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
	   * for more info.
	   * 
	   * @param term <tt>String</tt> of the search term to be queried
	   * @param location <tt>String</tt> of the location
	   * @return <tt>String</tt> JSON Response
	   */
	  public String searchForBusinessesByLocation(
			  final String term, 
			  final String location,
			  final String longitude,
			  final String latitude,
			  final String minRating,
			  final String restaurantsOnly,
			  final String fullBar,
			  final int limit, 
			  final String offset) {
		_log.info("searchforBusinessByLocation, location: " + location + ", term: " + term + ", longitude: " + longitude + ", latitude: " + latitude+ 
				", liimit: " + limit + ", minRating: " + minRating + ", restaurantsOnly: " + restaurantsOnly + ", fullBar: " + fullBar + ", offset: " + offset);
	    OAuthRequest request = createOAuthRequest(SEARCH_PATH);
	    
	    StringBuilder category = new StringBuilder("");
	    if (restaurantsOnly.equalsIgnoreCase("true")) category.append("restaurants");
	    if (fullBar.equalsIgnoreCase("true")) {
	    	if (category.length() > 0) category.append(",");
	    	category.append("beer_and_wine");
	    }
	    
	    request.addQuerystringParameter("term", term);
	    request.addQuerystringParameter("location", location);
	    if (category.length() > 0) request.addQuerystringParameter("category_filter", category.toString());
	    request.addQuerystringParameter("limit", String.valueOf(limit));
	    request.addQuerystringParameter("offset",offset);
	    if (longitude != null) request.addQuerystringParameter("longitude",longitude);
	    if (latitude != null) request.addQuerystringParameter("latitude",latitude);
	    request.addQuerystringParameter("sort","1");
	    //request.addQuerystringParameter("radius_filter","8000");
	    return sendRequestAndGetResponse(request);
	  }
	 
	
	  /**
	   * Queries the Search API based on the command line arguments and takes the first result to query
	   * the Business API.
	   * 
	   * @param yelpApi <tt>YelpAPI</tt> service instance
	   * @param yelpApiCli <tt>YelpAPICLI</tt> command line arguments
	   */
	  private static String queryAPI(YelpHandler yelpApi, 
			  final Map<String,PlaceMarker> placeMarkers,
			  final String term,
			  final String location,
			  final String longitude,
			  final String latitude,
			  final String minRating,
			  final String restaurantsOnly,
			  final String fullBar,
			  final int limit,
			  final String offset) {
		
		  
		// sort by distance and use the following formula to calculate the distance
		// dlon = lon2 - lon1 
		// dlat = lat2 - lat1 
	    // a = (sin(dlat/2))^2 + cos(lat1) * cos(lat2) * (sin(dlon/2))^2 
	    // c = 2 * atan2( sqrt(a), sqrt(1-a) ) 
	    // d = R * c (where R is the radius of the Earth)
		// The values used for the radius of the Earth (3961 miles & 6373 km) are optimized for locations around 39 degrees from the equator (roughly the Latitude of Washington, DC, USA).
		  
	    String searchResponseJSON =
	        yelpApi.searchForBusinessesByLocation(term, location, longitude,latitude, minRating,
	        		restaurantsOnly, fullBar, limit, offset);
	    
	    StringBuilder result = new StringBuilder("");

	    JSONParser parser = new JSONParser();
	    JSONObject response = null;
	    try {
	      response = (JSONObject) parser.parse(searchResponseJSON);
	      if (response == null) {
	    	  result.append("Error: no response returned.\n");
	    	  return result.toString();
	      }
	    } catch (ParseException pe) {
	    	result.append("Error: could not parse JSON response:\n");
	    	result.append(searchResponseJSON).append("\n");
	    	return result.toString();
	    }

	    JSONArray businesses = (JSONArray) response.get("businesses");
	    
	    if (businesses == null) {
	    	result.append("Error: could not parse JSON response:\n");
	    	result.append(searchResponseJSON).append("\n");
	    	return result.toString();
	    	
	    }
	    
	    Map<String,Map<String,String>> map = new TreeMap<String,Map<String,String>>(Collections.reverseOrder());
	    
	    
	    for (int i=0; i < businesses.size(); i++) {
	    	JSONObject business = (JSONObject)businesses.get(i);
	    	
	    	final String rating = business.get("rating").toString();
	    	final String id = business.get("id").toString();
	    	if (rating.compareTo(minRating) >= 0) {
		    	final String businessName = normalize(business.get("name").toString());
		    	final String url = business.get("url").toString();
		    	final String openStatus = business.get("is_closed").toString().equalsIgnoreCase("true") ? "closed" : "open";
		    	final JSONObject locationInfo = (JSONObject)business.get("location");
		    	final String address = ((JSONArray)locationInfo.get("display_address")).get(0).toString();
		    	final boolean selected = (placeMarkers != null && placeMarkers.get(id) != null); 
		    	if (selected) {
		    		placeMarkers.remove(id);
		    	}
		    	//String distance = business.get("distance").toString();
		    	//_log.info("distance: " + distance);
		    	Map<String,String> items = map.get(rating);
		    	if (items == null) items = new TreeMap<String,String>();
		    	items.put(businessName,url+",,"+openStatus+",,"+address+",,"+id+",,"+selected);
		    	map.put(rating, items);
	    	}
	    	if (placeMarkers != null) {
		    	for (PlaceMarker placeMarker : placeMarkers.values()) {
		    		Map<String,String> items = map.get(Constant.UNKNOWN_RATING);
		    		if (items == null) items = new TreeMap<String,String>();
		    		items.put(placeMarker.getName(),placeMarker.getUrl()+",,"+"unknown"+",,"+placeMarker.getAddress()+",,"+placeMarker.getId()+",,true");
		    		map.put(Constant.UNKNOWN_RATING, items);
		    	}
	    	}
	    	
	    }
	    
	    // build output
	    // I will consider adding this later.
	    //result.append("<optgroup label=\"<a href='#' class='click-more'>Check for more</a>\">\n");
	    
	    // handle unknown rating
	    result.append("<optgroup label=\"")
	    	.append("<div>Rating (Unknown)</div>\n");
	    
	    Map<String,String> items = map.get(Constant.UNKNOWN_RATING);
	    if (items != null) {
	    	for (String businessName : items.keySet()) {
	    		
	    		String[] parts = items.get(businessName).split(",,");
	    		
	    		result.append("<option value=\"").append(businessName.replaceAll("\"", "'"))
	    		.append("\" title='click view to check out' data-url=\"")
	    		.append(parts[0])
	    		.append("\" data-id=\"")
	    		.append(parts[3])
	    		.append("\"")
	    		.append(" data-address=\"")
	    		.append(parts[1].replaceAll("\"",  "'"))
	    		.append("\"");
	    		
	    		
	    		if (parts[4].equalsIgnoreCase("true")) {
	    			result.append(Constant.SELECTED);
	    		}
	    		
	    		result.append(">")
	    		.append(businessName)
	    		.append(", ").append(parts[2]).append(", ").append(parts[1])
	    		.append("</option>");
	    	}
	    	
	    	result.append("</optgroup>\n");
	    }
	    
	    
	    for (String rating : map.keySet()) {
	    	
	    	// we'll handle this one first, so we will skip.
	    	if (rating.equals(Constant.UNKNOWN_RATING)) continue;
	    	
	    	result.append("<optgroup label=\"")	
	    	.append("<div class='rating-very-large'>")
	    	.append("<i class='star-img rating_")
	    	.append(rating.toString().replaceAll("\\.", "_")).append("_star-rating' title='")
	    	.append(rating).append(" star rating'>")
	    	.append("<img class='offscreen' width='84' height='303' alt='")
	    	.append(rating).append(" star rating' src='/images/stars_map.png'>")
	    	.append("</i></div>\">\n");
	
	    	items = map.get(rating);
	    	for (String businessName : items.keySet()) {
	    		
	    		String[] parts = items.get(businessName).split(",,");
	    		
	    		result.append("<option value=\"").append(businessName.replaceAll("\"", "'"))
	    		.append("\" title='click view to check out' data-url=\"")
	    		.append(parts[0])
	    		.append("\" data-id=\"")
	    		.append(parts[3])
	    		.append("\"")
	    		.append(" data-address=\"")
	    		.append(parts[1].replaceAll("\"",  "'"))
	    		.append("\"");
	    		
	    		
	    		if (parts[4].equalsIgnoreCase("true")) {
	    			result.append(Constant.SELECTED);
	    		}
	    		
	    		result.append(">")
	    		.append(businessName)
	    		.append(", ").append(parts[2]).append(", ").append(parts[1])
	    		.append("</option>");
	    	}
	    	
	    	result.append("</optgroup>\n");
	    	
	    }
	    
	    

	    return result.toString();
	    
	}		
	
	public static String getPlaceListAsHtml(
			final Map<String,PlaceMarker> placeMarkers,
			final String term, 
			final String location, 
			final String longitude, 
			final String latitude, 
			final String minRating,
			final String restaurantsOnly,
			final String fullBar,
			final int limit, 
			final String offset) {
		YelpHandler yelpHandler = new YelpHandler(Secret.CONSUMER_KEY, Secret.CONSUMER_SECRET, Secret.TOKEN, Secret.TOKEN_SECRET);
		
		String result = queryAPI(yelpHandler,placeMarkers,term,location,longitude,latitude,minRating,restaurantsOnly,
				fullBar,limit,offset);
		
		return result;
		
	}

}

package com.happyhourplanner.yelp;

import java.text.Normalizer;

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

import com.happyhourplanner.key.Secret;
import com.happyhourplanner.key.TwoStepOAuth;
import com.happyhourplanner.key.YelpAPI;

public class YelpHandler {
	
	private static final int SEARCH_LIMIT =5;
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
	  public String searchForBusinessesByLocation(String term, String location) {
	    OAuthRequest request = createOAuthRequest(SEARCH_PATH);
	    request.addQuerystringParameter("term", term);
	    request.addQuerystringParameter("location", location);
	    request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
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
			  final String term,
			  final String location) {
	    String searchResponseJSON =
	        yelpApi.searchForBusinessesByLocation(term, location);
	    
	    StringBuilder result = new StringBuilder("");

	    JSONParser parser = new JSONParser();
	    JSONObject response = null;
	    try {
	      response = (JSONObject) parser.parse(searchResponseJSON);
	    } catch (ParseException pe) {
	    	result.append("Error: could not parse JSON response:\n");
	    	result.append(searchResponseJSON).append("\n");
	      //System.out.println("Error: could not parse JSON response:");
	      //System.out.println(searchResponseJSON);
	      //System.exit(1);
	    }
	    
	   

	    JSONArray businesses = (JSONArray) response.get("businesses");
	    for (int i=0; i < businesses.size(); i++) {
	    	JSONObject business = (JSONObject)businesses.get(i);
	    	result.append((i+1)).append(". ").append(normalize(business.get("name").toString())).append("<br/>");
	    }
	    //JSONObject firstBusiness = (JSONObject) businesses.get(0);
	    //String firstBusinessID = firstBusiness.get("id").toString();
	    //System.out.println(String.format(
	    //    "%s businesses found, querying business info for the top result \"%s\" ...",
	    //    businesses.size(), firstBusinessID));
	   // result.append(String.format(
	    //		"%s businesses found, querying business info for the top result \"%s\" ...",
	    //		businesses.size(), firstBusinessID)).append("\n");

	    // Select the first business and display business details
	    //String businessResponseJSON = yelpApi.searchByBusinessId(firstBusinessID.toString());
	    //System.out.println(String.format("Result for business \"%s\" found:", firstBusinessID));
	    
	    //System.out.println(businessResponseJSON);
	    //result.append(String.format("Result for business \"%s\" found:",firstBusinessID)).append("\n");
	    //result.append(businesses.toString());
	    return result.toString();
	  }
	  
	public static String getPlaceListAsJson(final YelpHandler handler, final String term, final String location) {
		return queryAPI(handler,term,location);
	}
	
	public static String getPlaceListAsHtml(final String term, final String location) {
		
		YelpHandler yelpHandler = new YelpHandler(Secret.CONSUMER_KEY, Secret.CONSUMER_SECRET, Secret.TOKEN, Secret.TOKEN_SECRET);
		
		return getPlaceListAsJson(yelpHandler,term,location);
	}

}

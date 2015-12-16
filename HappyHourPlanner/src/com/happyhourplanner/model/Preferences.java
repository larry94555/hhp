package com.happyhourplanner.model;

import javax.persistence.Embeddable;

import com.happyhourplanner.common.Constant;

@Embeddable
public class Preferences {
	private boolean allowSuggestions;
	private boolean useMinRating;
	private String minRating;
	private boolean restaurantsOnly;
	private boolean fullBar;
	private String availability;
	
	public Preferences() {
		
		// default settings
		allowSuggestions = true;
		useMinRating = true;
		minRating = "2.0";
		restaurantsOnly = false;
		fullBar = false;
		availability="MoTuWeThFr";
		
	}
	
	public Preferences(
			
			final boolean allowSuggestions,
			final boolean useMinRating,
			final String minRating,
			final boolean restaurantsOnly,
			final boolean fullBar,
			final String availability
			
			) {
		
		this.allowSuggestions = allowSuggestions;
		this.useMinRating = useMinRating;
		this.minRating = minRating;
		this.restaurantsOnly = restaurantsOnly;
		this.fullBar = fullBar;
		this.availability = availability;
		
	}
	
	public void setAllowSuggestions(final boolean allowSuggestions) { this.allowSuggestions = allowSuggestions; }
	public void setUseMinRating(final boolean useMinRating) { this.useMinRating = useMinRating; }
	public void setMinRating(final String minRating) { this.minRating = minRating; }
	public void setRestaurantsOnly(final boolean restaurantsOnly) { this.restaurantsOnly = restaurantsOnly; }
	public void setFullBar(final boolean fullBar) { this.fullBar = fullBar; }
	public void setAvailability(final String availability)  {this.availability = availability; }
	
	public boolean isAllowSuggestions() { return allowSuggestions; }
	public boolean isUseMinRating() { return useMinRating; }
	public String getMinRating() {
		if (minRating == null) {
			return Constant.EMPTY_STRING;
		}
		else {
			return minRating;
		}
	}
	public boolean isRestaurantsOnly() { return restaurantsOnly; }
	public boolean isFullBar() { return fullBar; }
	public String getAvailability() {
		if (availability == null) {
			return Constant.EMPTY_STRING;
		}
		else {
			return availability;
		}
	}
	
	public String debug() {
		StringBuilder result = new StringBuilder();
		result.append("allowSuggestions: ").append(allowSuggestions).append(", ")
			.append("useMinRating: ").append(useMinRating).append(", ")
			.append("minRating: ").append(minRating).append(", ")
			.append("restaurantsOnly: ").append(restaurantsOnly).append(", ")
			.append("fullBar: ").append(fullBar).append(", ")
			.append("availability: ").append(availability);
		
		return result.toString();
	}

}

package com.happyhourplanner.model;

import javax.persistence.Embeddable;

@Embeddable
public class PlaceMarker {
	
	private String id;
	private String url;
	private String name;
	
	public PlaceMarker(final String id, final String url, final String name) {
		this.id = id;
		this.url = url;
		this.name = name;
	}
	
	public String getId() { return id; }
	
	public String getUrl() { return url; }
	
	public String getName() { return name; }
	
	public void setId(String id) { this.id = id; }
	
	public void setUrl(String url) { this.url = url; }
	
	public void setName(String name) { this.name = name; }
	
	public String debug() {
		StringBuilder builder = new StringBuilder();
		builder.append("id = ").append(id).append(", url = ").append(url).append(", name = ").append(name);
		return builder.toString();
	}
	

}

package com.happyhourplanner.model;

import javax.persistence.Embeddable;

@Embeddable
public class PlaceMarker {
	
	private String id;
	private String url;
	private String name;
	private String address;
	
	public PlaceMarker(final String id, final String url, final String name, final String address) {
		this.id = id;
		this.url = url;
		this.name = name;
		this.address = address;
	}
	
	public String getId() { return id; }
	
	public String getUrl() { return url; }
	
	public String getName() { return name; }
	
	public String getAddress() { return address; }
	
	public void setId(String id) { this.id = id; }
	
	public void setUrl(final String url) { this.url = url; }
	
	public void setName(final String name) { this.name = name; }
	
	public void setAddress(final String address) { this.address = address; }
	
	public String debug() {
		StringBuilder builder = new StringBuilder();
		builder.append("id = ").append(id).append(", url = ").append(url).append(", name = ")
		.append(name).append(", address = ").append(address);
		return builder.toString();
	}
	

}

package com.happyhourplanner.model;

import javax.persistence.Embeddable;

public class PlaceMarker {
	
	private String id;
	private String url;
	private String name;
	private String address;
	
	public PlaceMarker()
	{
		this.id=null;
		this.url=null;
		this.name=null;
		this.address=null;
	}
	
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
	
	public String toStringForm() {
		StringBuilder builder = new StringBuilder(url).append(",,")
				.append("unknown").append(",,")
				.append(address).append(",,")
				.append(id).append(",,")
				.append("true").append(",,")
				.append(name);
		return builder.toString();
	}
	
	public static PlaceMarker fromStringForm(final String stringForm) {
		
		String[] parts = stringForm.split(",,");
		
		return new PlaceMarker(
				parts[3], // id
				parts[0], // url
				parts[5], // name
				parts[2]  // address
		);
	}
	
	public String debug() {
		StringBuilder builder = new StringBuilder();
		builder.append("id = ").append(id).append(", url = ").append(url).append(", name = ")
		.append(name).append(", address = ").append(address);
		return builder.toString();
	}
	

}

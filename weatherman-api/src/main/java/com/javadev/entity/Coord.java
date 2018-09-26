package com.javadev.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord {
	
	String lon;
	String lat;
	
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	@Override
	public String toString() {
		return "Coord [lon=" + lon + ", lat=" + lat + "]";
	}
	
	

}

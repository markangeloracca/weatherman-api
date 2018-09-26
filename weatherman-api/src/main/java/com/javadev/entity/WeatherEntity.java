package com.javadev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherEntity {
	
	@JsonIgnore
	String responseId;
	String location;
	String actualWeather;
	String temperature;
	
	
	
	public WeatherEntity() {
		
	}
	
	public WeatherEntity( String location, String actualWeather, String temperature ) {
		super();
		this.location = location;
		this.actualWeather = actualWeather;
		this.temperature = temperature;
	}
	
	
	public String getResponseId() {
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getActualWeather() {
		return actualWeather;
	}
	public void setActualWeather(String actualWeather) {
		this.actualWeather = actualWeather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}





}

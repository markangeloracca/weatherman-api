package com.javadev.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "WeatherLog")
public class WeatherLog extends WeatherEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	long id;
	Date dTimeInserted;
	
	String responseId;
	String location;
	String actualWeather;
	String temperature;
	
	
	public WeatherLog() {
		
	}
	
	public WeatherLog(String responseId, String location, String actualWeather, String temperature) {
		super();
		this.responseId = responseId;
		this.location = location;
		this.actualWeather = actualWeather;
		this.temperature = temperature;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Date getdTimeInserted() {
		return dTimeInserted;
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
	public void setdTimeInserted(Date dTimeInserted) {
		this.dTimeInserted = dTimeInserted;
	}
	
	@Override
	public String toString() {
		return "WeatherLog [id=" + id + ", dTimeInserted=" + dTimeInserted + ", responseId=" + responseId
				+ ", location=" + location + ", actualWeather=" + actualWeather + ", temperature=" + temperature + "]";
	}
}

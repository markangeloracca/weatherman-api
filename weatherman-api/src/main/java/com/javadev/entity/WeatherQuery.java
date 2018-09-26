package com.javadev.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherQuery {
	
	String id;
	String name;
	String cod;
	
	List<Weather> weather;
	Main main;
	String dt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	@Override
	public String toString() {
		return "WeatherQuery [id=" + id + ", name=" + name + ", cod=" + cod + ", weather=" + weather + ", main=" + main
				+ ", dt=" + dt + "]";
	}

	
	
	
}

package com.javadev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javadev.entity.WeatherEntity;
import com.javadev.entity.WeatherLog;
import com.javadev.service.WeatherService;

@RestController
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping("/weather/{city}")
	public WeatherEntity getCityWeather(@PathVariable String city) {
		return weatherService.getCityWeather( city );
	}
	
	@RequestMapping("/weather/tricity")
	public List<WeatherEntity> getTriCityWeather() {
		return weatherService.getTriCityWeather();
	}
	
	@RequestMapping("/weather/alllogs")
	public List<WeatherLog> getAllWeatherLog() {
		return weatherService.getAllWeatherLog();
	}
	
}

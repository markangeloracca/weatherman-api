package com.javadev.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.javadev.entity.Weather;
import com.javadev.entity.WeatherEntity;
import com.javadev.entity.WeatherQuery;

@Component
public class OpenWeatherMapUtils {
	
	private static final Logger log = LoggerFactory.getLogger(OpenWeatherMapUtils.class);
	
	
	private static String API_URL;
	
	
	private static String UNITS_FORMAT;
	
	
	private static String API_KEY;
	
	@Value("${openweathermap.api.url}")
	public void setApiUrl( String apiUrl ) {
		API_URL = apiUrl;
	}
	
	@Value("${openweathermap.api.key}")
	public void setApiKey( String apiKey ) {
		API_KEY = apiKey;
	}
	
	@Value("${openweathermap.units.format}")
	public void setUnitsFormat( String unitsFormat ) {
		UNITS_FORMAT = unitsFormat;
	}
	
	public static WeatherEntity parseWeatherEntity( WeatherQuery weatherQuery ) {
		WeatherEntity weatherEntity = new WeatherEntity();
		
		weatherEntity.setLocation( weatherQuery.getName() );
		
		List<Weather> weatherList = weatherQuery.getWeather();
		
		String actualWeather = weatherList.get( 0 ).getDescription(); 
		weatherEntity.setActualWeather( actualWeather );
		weatherEntity.setTemperature(  weatherQuery.getMain().getTemp() + "");
		weatherEntity.setResponseId( UUID.randomUUID().toString() );
		return weatherEntity;
	}
	
	public static String initCityWeatherRestQueryTemplate() {
		StringBuilder restQuery = new StringBuilder();
		if( !ObjectUtils.isEmpty( API_URL ) ) {
			restQuery.append( API_URL ).append("?q=");
		} else {
			log.error( "Invalid OpenWeatherMap Url." );
		}
		
		restQuery.append( "%s");
		
		if( !ObjectUtils.isEmpty( API_KEY ) ) {
			restQuery.append("&APPID=").append( API_KEY);
		} else {
			log.error( "Invalid API Key Parameter" );
		}
		
		if( !ObjectUtils.isEmpty( UNITS_FORMAT ) ) {
			restQuery.append("&units=").append( UNITS_FORMAT );
		} else {
			log.error( "Invalid Units Format Parameter" );
		}
		
		log.info("REST QUERY: " + restQuery.toString());
		return restQuery.toString();
	}
	
	public static String getWeatherUniqueCriteriaMD5( WeatherEntity weatherEntity) {
		
		String value = weatherEntity.getLocation() + weatherEntity.getActualWeather() + weatherEntity.getTemperature();	
		return getMD5( value );
	}
	
	private static String getMD5( String str ) {
		MessageDigest md;
		String hash = new String();
		byte[] digest;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update( str.getBytes() );
			digest = md.digest();
			hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
	
	public static String getWeatherId(  WeatherQuery weatherQuery  ) {
		String location = weatherQuery.getName();
		String actualWeather = weatherQuery.getWeather().get( 0 ).getDescription();
		String temp = weatherQuery.getMain().getTemp() + "";
		String dt = weatherQuery.getDt();
		String value = location + actualWeather + temp + dt;
		
		return getMD5( value );
	}

}

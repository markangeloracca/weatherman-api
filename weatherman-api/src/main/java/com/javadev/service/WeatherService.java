package com.javadev.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.javadev.entity.WeatherEntity;
import com.javadev.entity.WeatherLog;
import com.javadev.entity.WeatherQuery;
import com.javadev.repository.WeatherLogRepository;
import com.javadev.util.OpenWeatherMapUtils;

@Service
public class WeatherService {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

	@Value("${openweathermap.city}")
	private String city;
	
	@Autowired
	private WeatherLogRepository weatherLogRepository;
	
	private static String restQueryTemplate;

	public List<WeatherEntity> getTriCityWeather() {
		

		List<WeatherEntity> triCityWeather = new ArrayList<>();
		String[] cities = city.split(",");
		
		if( cities.length < 3) {
			log.info( "Cities are not properly defined. Please check application.properties." );
			return new ArrayList<>();
		}
		
		WeatherEntity weatherEntity;
		for( String item : cities ) {
			
			if( ObjectUtils.isEmpty( item ) ) {
				continue;
			} else {
				weatherEntity = getCityWeather( item );
			}
			
			if ( !ObjectUtils.isEmpty( weatherEntity  ) ) {
				triCityWeather.add( weatherEntity );
			}
		}
		return triCityWeather;
	}
	
	public WeatherEntity getCityWeather( String city ) {

		restQueryTemplate = OpenWeatherMapUtils.initCityWeatherRestQueryTemplate();

		if( ObjectUtils.isEmpty( restQueryTemplate ) ) {
			return new WeatherEntity();
		}
		
		RestTemplate restTemplate = new RestTemplate();
		WeatherQuery weatherQuery = restTemplate.getForObject(String.format( restQueryTemplate, city ), WeatherQuery.class);
        log.info(weatherQuery.toString());
    
        WeatherEntity weatherEntity = OpenWeatherMapUtils.parseWeatherEntity( weatherQuery );
        
        if( isWeatherLogUnique( weatherEntity ) ) {
        	addWeatherLog( weatherEntity );
        }

		return weatherEntity;
	}
	
	private boolean isWeatherLogUnique( WeatherEntity weatherEntity ) {
		List<WeatherEntity> weatherList = new ArrayList<>();
		weatherLogRepository.findAll().forEach( weatherList::add);
		String weatherMD5 = OpenWeatherMapUtils.getWeatherUniqueCriteriaMD5(weatherEntity);
		
		if( ObjectUtils.isEmpty( weatherMD5 ) ) {
			return false;
		} 
		
		String itemMD5;
		for( WeatherEntity item : weatherList ) {
			itemMD5 = OpenWeatherMapUtils.getWeatherUniqueCriteriaMD5( item );
			if( ObjectUtils.isEmpty( itemMD5 ) ) {
				continue;
			} else if( itemMD5.equals( weatherMD5) ) {
				return false;
			}
		}
		return true;
	}
	
	public void addWeatherLog( WeatherEntity weatherEntity ) {

		WeatherLog weatherLog = new WeatherLog( 
				weatherEntity.getResponseId(),
				weatherEntity.getLocation(),
				weatherEntity.getActualWeather(),
				weatherEntity.getTemperature() );

		Date dt = new Date();
		weatherLog.setdTimeInserted( dt );
		
		if( weatherLogRepository.count() > 4 ) {
			List<WeatherLog> weatherList = new ArrayList<>();
			weatherLogRepository.findAll().forEach( weatherList::add);
			weatherLogRepository.delete( weatherList.get(0) );
		}
		weatherLogRepository.save( weatherLog );
	}
	
	public List<WeatherLog> getAllWeatherLog(){
		List<WeatherLog> weatherLogList = new ArrayList<>();
		weatherLogRepository.findAll().forEach( weatherLogList::add);
		return weatherLogList;
	}
	
}

package com.javadev.repository;

import org.springframework.data.repository.CrudRepository;

import com.javadev.entity.WeatherLog;

public interface WeatherLogRepository extends CrudRepository<WeatherLog, String>
{

}

package com.javadev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.javadev.entity.Quote;

@SpringBootApplication
public class WeathermanApiApplication {

	private static final Logger log = LoggerFactory.getLogger(WeathermanApiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(WeathermanApiApplication.class, args);

	}
}

# WEATHERMAN - SPRING BOOT RESTFUL API

**Language**: Java
**Framework**: Spring Boot
**Description**: A microservice which serves a restful API by consuming weather information from https://openweathermap.org/api
1. The API displays list of weather information from London, Prague and San Francisco. Information includes location, actual weather and temperature. Response is in JSON format.
2. The API stores the last five unique responses of the API specified in item #1. Information should be saved in DB Table described below.

Table Name: **WeatherLog**
| Name | Data Type |
|---|---|
| Id (unique auto number) | long |
| responseId (unique guid) | String |
| location | String |
| actualWeather | String |
| temperature | String |
| dtimeInserted | timestamp |

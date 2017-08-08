/*
 * 
 */
package com.crossover.trial.weather.service;


/**
 * The Interface WeatherQueryService.
 * 
 * @author Ibrahim OGUZ
 */
public interface WeatherQueryService {
	
	/**
	 * Retrieve health and status information for the the query api. Returns information about how the number
     * of datapoints currently held in memory, the frequency of requests for each IATA code and the frequency of
     * requests for each radius.
	 *
	 * @return a JSON formatted dict with health information.
	 */
	String ping();
	
	/**
	 * Retrieve the most up to date atmospheric information from the given airport and other airports in the given
     * radius.
	 *
	 * @param iata the three letter airport code
	 * @param radiusString radiusString the radius, in km, from which to collect weather data
	 * @return list of  from the requested airport and airports in the given radius
	 */
	String weather(String iata, String radiusString);
}

/*
 * 
 */
package com.crossover.trial.weather.service;


/**
 * The interface shared to airport weather collection systems.
 *
 * @author Ibrahim OGUZ
 */
public interface WeatherCollectService {

	/**
	 * Update weather.
	 *
	 * @param iataCode the iata code
	 * @param pointType the point type
	 * @param datapointJson the datapoint json
	 * @return true, if successful
	 */
	boolean updateWeather(String iataCode, String pointType, String datapointJson);

	/**
	 * Gets the airports.
	 *
	 * @return the airports
	 */
	String getAirports();

	/**
	 * Gets the airport.
	 *
	 * @param iata the iata
	 * @return the airport
	 */
	String getAirport(String iata);

	/**
	 * Adds the airport.
	 *
	 * @param iata the iata
	 * @param latitude the latitude
	 * @param longtitude the longtitude
	 * @param city the city
	 * @param country the country
	 * @param icao the icao
	 * @param altitude the altitude
	 * @param timezone the timezone
	 * @param dst the dst
	 * @param name the name
	 * @return true, if successful
	 */
	boolean addAirport(String iata, Double latitude, Double longtitude, String city, String country, String icao,
			Double altitude, Double timezone, String dst, String name);

	/**
	 * Delete airport.
	 *
	 * @param iata the iata
	 * @return true, if successful
	 */
	boolean deleteAirport(String iata);
}

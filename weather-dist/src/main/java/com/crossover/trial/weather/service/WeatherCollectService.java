package com.crossover.trial.weather.service;

/**
 * The interface shared to airport weather collection systems.
 *
 * @author Ibrahim OGUZ
 */
public interface WeatherCollectService {

	boolean updateWeather(String iataCode, String pointType, String datapointJson);

	String getAirports();

	String getAirport(String iata);

	boolean addAirport(String iata, Double latitude, Double longtitude, String city, String country, String icao,
			Double altitude, Double timezone, String dst, String name);

	boolean deleteAirport(String iata);
}

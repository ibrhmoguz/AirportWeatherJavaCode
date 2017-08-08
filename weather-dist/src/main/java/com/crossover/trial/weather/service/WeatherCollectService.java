package com.crossover.trial.weather.service;

/**
 * The interface shared to airport weather collection systems.
 *
 * @author Ibrahim OGUZ
 */
public interface WeatherCollectService {

	void updateWeather(String iataCode, String pointType, String datapointJson);

	String getAirports();

	String getAirport(String iata);

	void addAirport(String iata, Double latitude, Double longtitude, String city, String country, String icao,
			Double altitude, Double timezone, String dst, String name);

	void deleteAirport(String iata);
}

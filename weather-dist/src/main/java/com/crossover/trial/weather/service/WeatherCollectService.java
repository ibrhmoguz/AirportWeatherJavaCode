package com.crossover.trial.weather.service;

import com.crossover.trial.weather.exception.WeatherException;
import com.crossover.trial.weather.model.AirportData;
import com.google.gson.JsonSyntaxException;

/**
 * The interface shared to airport weather collection systems.
 *
 * @author Ibrahim OGUZ
 */
public interface WeatherCollectService {

	void updateWeather(String iataCode, String pointType, String datapointJson) throws WeatherException;

	String getAirports();

	String getAirport(String iata);

	void addAirport(String iata, Double latString, Double longString);
	
	void deleteAirport(String iata);
}

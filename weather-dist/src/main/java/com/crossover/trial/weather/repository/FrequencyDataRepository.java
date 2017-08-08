/*
 * 
 */
package com.crossover.trial.weather.repository;

import java.util.Map;

import com.crossover.trial.weather.model.AirportData;


/**
 * The Interface FrequencyDataRepository.
 */
public interface FrequencyDataRepository {
	
	/**
	 * Gets the request frequency.
	 *
	 * @param airport the airport
	 * @return the request frequency
	 */
	int getRequestFrequency(AirportData airport);

	/**
	 * Adds the request frequency.
	 *
	 * @param airport the airport
	 */
	void addRequestFrequency(AirportData airport);

	/**
	 * Gets the radius freq.
	 *
	 * @return the radius freq
	 */
	Map<Double, Integer> getRadiusFreq();
	
	/**
	 * Gets the request frequency size.
	 *
	 * @return the request frequency size
	 */
	int getRequestFrequencySize();
	
	/**
	 * Gets the radius frequency.
	 *
	 * @param data the data
	 * @return the radius frequency
	 */
	int getRadiusFrequency(Double data);
	
	/**
	 * Adds the radius frequency.
	 *
	 * @param data the data
	 */
	void addRadiusFrequency(Double data);
}

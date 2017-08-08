/*
 * 
 */
package com.crossover.trial.weather.repository;

import java.util.List;

import com.crossover.trial.weather.model.AirportData;


/**
 * The Interface AirportDataRepository.
 */
public interface AirportDataRepository {

	/**
	 * Adds the airport.
	 *
	 * @param airportData the airport data
	 */
	void addAirport(AirportData airportData);

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
	AirportData getAirport(String iata);

	/**
	 * Delete airport.
	 *
	 * @param iata the iata
	 */
	void deleteAirport(String iata);

	/**
	 * Gets the airport data idx.
	 *
	 * @param iataCode the iata code
	 * @return the airport data idx
	 */
	int getAirportDataIdx(String iataCode);
	
	/**
	 * Gets the airport data list.
	 *
	 * @return the airport data list
	 */
	List<AirportData> getAirportDataList();
}

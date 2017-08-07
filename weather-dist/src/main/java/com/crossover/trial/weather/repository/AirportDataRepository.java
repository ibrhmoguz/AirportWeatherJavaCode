package com.crossover.trial.weather.repository;

import java.util.List;

import com.crossover.trial.weather.model.AirportData;

public interface AirportDataRepository {

	void addAirport(AirportData airportData);

	String getAirports();

	AirportData getAirport(String iata);

	void deleteAirport(String iata);

	int getAirportDataIdx(String iataCode);
	
	List<AirportData> getAirportDataList();
}

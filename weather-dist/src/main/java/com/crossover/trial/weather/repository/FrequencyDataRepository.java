package com.crossover.trial.weather.repository;

import java.util.Map;

import com.crossover.trial.weather.model.AirportData;

public interface FrequencyDataRepository {
	int getRequestFrequency(AirportData airport);

	void addRequestFrequency(AirportData airport);

	Map<Double, Integer> getRadiusFreq();
	
	int getRequestFrequencySize();
	
	int getRadiusFrequency(Double data);
	
	void addRadiusFrequency(Double data);
}

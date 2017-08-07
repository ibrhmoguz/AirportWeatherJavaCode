package com.crossover.trial.weather.repository;

import java.util.Map;

import com.crossover.trial.weather.model.AirportData;

public interface FrequencyDataRepository {
	int getRequestFrequency(AirportData airport, int value);

	void addRequestFrequency(AirportData airport, int value);

	Map<Double, Integer> getRadiusFreq();
}

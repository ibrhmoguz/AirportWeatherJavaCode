package com.crossover.trial.weather.repository;

import java.util.Map;

import com.crossover.trial.weather.model.AtmosphericInformation;

public interface AtmosphericDataRepository {

	Map<Integer, AtmosphericInformation> getAtmosphericInformation();

	AtmosphericInformation getAtmosphericInformation(int idx);

	void addAtmosphericInformation(Integer idx, AtmosphericInformation atmosphericInformation);
}

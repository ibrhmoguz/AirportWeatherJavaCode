package com.crossover.trial.weather.repository;

import java.util.List;

import com.crossover.trial.weather.model.AtmosphericInformation;

public interface AtmosphericDataRepository {

	List<AtmosphericInformation> getAtmosphericInformation();

	AtmosphericInformation getAtmosphericInformation(int idx);
}

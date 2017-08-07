package com.crossover.trial.weather.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

import com.crossover.trial.weather.exception.AtmosphericInformationNotFound;
import com.crossover.trial.weather.model.AtmosphericInformation;

@Singleton
public class AtmosphericDataRepositoryImpl implements AtmosphericDataRepository {

	private static final Map<Integer, AtmosphericInformation> atmosphericInformation = new HashMap<>();

	public AtmosphericDataRepositoryImpl() {
	}

	@Override
	public Map<Integer, AtmosphericInformation> getAtmosphericInformation() {
		return atmosphericInformation;
	}

	@Override
	public AtmosphericInformation getAtmosphericInformation(int idx) {
		if (AtmosphericDataRepositoryImpl.atmosphericInformation == null) {
			throw new AtmosphericInformationNotFound();
		}
		return AtmosphericDataRepositoryImpl.atmosphericInformation.get(idx);
	}

	@Override
	public void addAtmosphericInformation(Integer idx, AtmosphericInformation atmosphericInformation) {
		AtmosphericDataRepositoryImpl.atmosphericInformation.put(idx, atmosphericInformation);
	}
}

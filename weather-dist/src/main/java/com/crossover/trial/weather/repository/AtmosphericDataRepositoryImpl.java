package com.crossover.trial.weather.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.crossover.trial.weather.exception.AtmosphericInformationNotFound;
import com.crossover.trial.weather.model.AtmosphericInformation;

@ApplicationScoped
public class AtmosphericDataRepositoryImpl implements AtmosphericDataRepository {

	private Map<Integer, AtmosphericInformation> atmosphericInformation;

	public AtmosphericDataRepositoryImpl() {
		this.atmosphericInformation = new HashMap<>();
	}

	@Override
	public Map<Integer, AtmosphericInformation> getAtmosphericInformation() {
		return atmosphericInformation;
	}

	@Override
	public AtmosphericInformation getAtmosphericInformation(int idx) {
		if (this.atmosphericInformation == null) {
			throw new AtmosphericInformationNotFound();
		}
		return this.atmosphericInformation.get(idx);
	}

	@Override
	public void addAtmosphericInformation(Integer idx, AtmosphericInformation atmosphericInformation) {
		this.atmosphericInformation.put(idx, atmosphericInformation);
	}
}

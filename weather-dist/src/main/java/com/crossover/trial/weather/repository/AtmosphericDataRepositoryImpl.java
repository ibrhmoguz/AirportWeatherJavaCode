package com.crossover.trial.weather.repository;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.crossover.trial.weather.exception.AtmosphericInformationNotFound;
import com.crossover.trial.weather.model.AtmosphericInformation;

@ApplicationScoped
public class AtmosphericDataRepositoryImpl implements AtmosphericDataRepository {

	private List<AtmosphericInformation> atmosphericInformation;

	public AtmosphericDataRepositoryImpl() {
		this.atmosphericInformation = new LinkedList<>();
	}

	@Override
	public List<AtmosphericInformation> getAtmosphericInformation() {
		return atmosphericInformation;
	}

	@Override
	public AtmosphericInformation getAtmosphericInformation(int idx) {
		if (this.atmosphericInformation == null) {
			throw new AtmosphericInformationNotFound();
		}
		return this.atmosphericInformation.get(idx);
	}
}

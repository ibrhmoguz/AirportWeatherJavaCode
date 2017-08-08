/*
 * 
 */
package com.crossover.trial.weather.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.crossover.trial.weather.exception.AtmosphericInformationNotFound;
import com.crossover.trial.weather.model.AtmosphericInformation;


/**
 * The Class AtmosphericDataRepositoryImpl.
 */
@Singleton
public class AtmosphericDataRepositoryImpl implements AtmosphericDataRepository {

	/** The Constant atmosphericInformation. */
	private static final Map<Integer, AtmosphericInformation> atmosphericInformation = new HashMap<>();

	/**
	 * Instantiates a new atmospheric data repository impl.
	 */
	public AtmosphericDataRepositoryImpl() {
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AtmosphericDataRepository#getAtmosphericInformation()
	 */
	@Override
	public Map<Integer, AtmosphericInformation> getAtmosphericInformation() {
		return atmosphericInformation;
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AtmosphericDataRepository#getAtmosphericInformation(int)
	 */
	@Override
	public AtmosphericInformation getAtmosphericInformation(int idx) {
		if (AtmosphericDataRepositoryImpl.atmosphericInformation == null) {
			throw new AtmosphericInformationNotFound();
		}
		return AtmosphericDataRepositoryImpl.atmosphericInformation.get(idx);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AtmosphericDataRepository#addAtmosphericInformation(java.lang.Integer, com.crossover.trial.weather.model.AtmosphericInformation)
	 */
	@Override
	public void addAtmosphericInformation(Integer idx, AtmosphericInformation atmosphericInformation) {
		AtmosphericDataRepositoryImpl.atmosphericInformation.put(idx, atmosphericInformation);
	}
}

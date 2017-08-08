/*
 * 
 */
package com.crossover.trial.weather.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.crossover.trial.weather.exception.RadiusFrequencyNotFound;
import com.crossover.trial.weather.exception.RequestFrequencyNotFound;
import com.crossover.trial.weather.model.AirportData;


/**
 * The Class FrequencyDataRepositoryImpl.
 */
@Singleton
public class FrequencyDataRepositoryImpl implements FrequencyDataRepository {

	/** The Constant requestFrequency. */
	private static final Map<AirportData, Integer> requestFrequency = new HashMap<>();
	
	/** The Constant radiusFreq. */
	private static final Map<Double, Integer> radiusFreq = new HashMap<>();

	/**
	 * Instantiates a new frequency data repository impl.
	 */
	public FrequencyDataRepositoryImpl() {
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.FrequencyDataRepository#getRequestFrequency(com.crossover.trial.weather.model.AirportData)
	 */
	@Override
	public int getRequestFrequency(AirportData airport) {
		if (FrequencyDataRepositoryImpl.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		return FrequencyDataRepositoryImpl.requestFrequency.getOrDefault(airport, 0);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.FrequencyDataRepository#addRequestFrequency(com.crossover.trial.weather.model.AirportData)
	 */
	@Override
	public void addRequestFrequency(AirportData airport) {
		if (FrequencyDataRepositoryImpl.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		FrequencyDataRepositoryImpl.requestFrequency.put(airport, getRequestFrequency(airport) + 1);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.FrequencyDataRepository#getRadiusFreq()
	 */
	@Override
	public Map<Double, Integer> getRadiusFreq() {
		if (FrequencyDataRepositoryImpl.radiusFreq == null) {
			throw new RadiusFrequencyNotFound();
		}
		return radiusFreq;
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.FrequencyDataRepository#getRequestFrequencySize()
	 */
	@Override
	public int getRequestFrequencySize() {
		if (FrequencyDataRepositoryImpl.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		return FrequencyDataRepositoryImpl.requestFrequency.size();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.FrequencyDataRepository#getRadiusFrequency(java.lang.Double)
	 */
	@Override
	public int getRadiusFrequency(Double data) {
		if (FrequencyDataRepositoryImpl.radiusFreq == null) {
			throw new RadiusFrequencyNotFound();
		}
		return FrequencyDataRepositoryImpl.radiusFreq.getOrDefault(data, 0);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.FrequencyDataRepository#addRadiusFrequency(java.lang.Double)
	 */
	@Override
	public void addRadiusFrequency(Double data) {
		if (FrequencyDataRepositoryImpl.radiusFreq == null) {
			throw new RadiusFrequencyNotFound();
		}
		FrequencyDataRepositoryImpl.radiusFreq.put(data, getRadiusFrequency(data) + 1);
	}
}

package com.crossover.trial.weather.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.crossover.trial.weather.exception.RadiusFrequencyNotFound;
import com.crossover.trial.weather.exception.RequestFrequencyNotFound;
import com.crossover.trial.weather.model.AirportData;

@Singleton
public class FrequencyDataRepositoryImpl implements FrequencyDataRepository {

	private static final Map<AirportData, Integer> requestFrequency = new HashMap<>();
	private static final Map<Double, Integer> radiusFreq = new HashMap<>();

	public FrequencyDataRepositoryImpl() {
	}

	@Override
	public int getRequestFrequency(AirportData airport) {
		if (FrequencyDataRepositoryImpl.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		return FrequencyDataRepositoryImpl.requestFrequency.getOrDefault(airport, 0);
	}

	@Override
	public void addRequestFrequency(AirportData airport) {
		if (FrequencyDataRepositoryImpl.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		FrequencyDataRepositoryImpl.requestFrequency.put(airport, getRequestFrequency(airport) + 1);
	}

	@Override
	public Map<Double, Integer> getRadiusFreq() {
		if (FrequencyDataRepositoryImpl.radiusFreq == null) {
			throw new RadiusFrequencyNotFound();
		}
		return radiusFreq;
	}

	@Override
	public int getRequestFrequencySize() {
		if (FrequencyDataRepositoryImpl.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		return FrequencyDataRepositoryImpl.requestFrequency.size();
	}

	@Override
	public int getRadiusFrequency(Double data) {
		if (FrequencyDataRepositoryImpl.radiusFreq == null) {
			throw new RadiusFrequencyNotFound();
		}
		return FrequencyDataRepositoryImpl.radiusFreq.getOrDefault(data, 0);
	}

	@Override
	public void addRadiusFrequency(Double data) {
		if (FrequencyDataRepositoryImpl.radiusFreq == null) {
			throw new RadiusFrequencyNotFound();
		}
		FrequencyDataRepositoryImpl.radiusFreq.put(data, getRadiusFrequency(data) + 1);
	}
}

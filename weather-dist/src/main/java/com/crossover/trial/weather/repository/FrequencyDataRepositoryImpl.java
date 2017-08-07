package com.crossover.trial.weather.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.crossover.trial.weather.exception.RadiusFrequencyNotFound;
import com.crossover.trial.weather.exception.RequestFrequencyNotFound;
import com.crossover.trial.weather.model.AirportData;

@ApplicationScoped
public class FrequencyDataRepositoryImpl implements FrequencyDataRepository {

	public Map<AirportData, Integer> requestFrequency;

	public Map<Double, Integer> radiusFreq;

	public FrequencyDataRepositoryImpl() {
		requestFrequency = new HashMap<AirportData, Integer>();
		radiusFreq = new HashMap<Double, Integer>();
	}

	@Override
	public int getRequestFrequency(AirportData airport, int value) {
		if (this.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		return this.requestFrequency.getOrDefault(airport, 0);
	}

	@Override
	public void addRequestFrequency(AirportData airport, int value) {
		if (this.requestFrequency == null) {
			throw new RequestFrequencyNotFound();
		}
		this.requestFrequency.put(airport, value);
	}

	@Override
	public Map<Double, Integer> getRadiusFreq() {
		if (this.radiusFreq == null) {
			throw new RadiusFrequencyNotFound();
		}
		return radiusFreq;
	}
}

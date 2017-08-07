package com.crossover.trial.weather.service;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.crossover.trial.weather.exception.WeatherException;
import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;
import com.crossover.trial.weather.model.pointtype.AbstractDataPointType;
import com.crossover.trial.weather.model.pointtype.AbstractDataPointTypeFactory;
import com.crossover.trial.weather.repository.AirportDataRepository;
import com.crossover.trial.weather.repository.AtmosphericDataRepository;
import com.google.gson.Gson;

public class WeatherCollectServiceImpl implements WeatherCollectService {

	private static final Logger LOGGER = Logger.getLogger(WeatherCollectServiceImpl.class.getName());
	public static final Gson gson = new Gson();
	
	private final AirportDataRepository airportRepository;
	private final AtmosphericDataRepository atmosphericDataRepository;

	@Inject
	public WeatherCollectServiceImpl(AirportDataRepository repo, AtmosphericDataRepository repoAtmospheric) {
		this.airportRepository = repo;
		this.atmosphericDataRepository = repoAtmospheric;
	}

	@Override
	public void updateWeather(String iataCode, String pointType, String datapointJson) {
		int airportDataIdx = this.airportRepository.getAirportDataIdx(iataCode);
		AtmosphericInformation atmosphericInformation = this.atmosphericDataRepository
				.getAtmosphericInformation(airportDataIdx);
		updateAtmosphericInformation(atmosphericInformation, pointType, datapointJson);
	}

	@Override
	public String getAirports() {
		String retVal = this.airportRepository.getAirports();
		return gson.toJson(retVal);
	}

	@Override
	public String getAirport(String iata) {
		AirportData airport= this.airportRepository.getAirport(iata);
		return gson.toJson(airport);
	}

	@Override
	public void addAirport(String iata, Double latitude, Double longtitude) {		
		AirportData airport = new AirportData();
		airport.setIata(iata);
		airport.setLatitude(latitude);
		airport.setLongitude(longtitude);
		this.airportRepository.addAirport(airport);
		int airportDataIdx = this.airportRepository.getAirportDataIdx(iata);
		this.atmosphericDataRepository.addAtmosphericInformation(airportDataIdx, new AtmosphericInformation());
	}

	@Override
	public void deleteAirport(String iata) {
		this.airportRepository.deleteAirport(iata);
	}

	private void updateAtmosphericInformation(AtmosphericInformation atmosphericInformation, String pointType,
			String datapointJson) {

		AbstractDataPointType dataPointType = AbstractDataPointTypeFactory.getDataPointType(pointType);
		boolean result = dataPointType.setAtmosphericInformation(atmosphericInformation,
				gson.fromJson(datapointJson, DataPoint.class));
		if (!result) {
			throw new WeatherException("Couldn't update atmospheric data");
		}
	}
}

package com.crossover.trial.weather.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.repository.AirportDataRepository;

public class WeatherQueryServiceImpl implements WeatherQueryService {

	private static final Logger LOGGER = Logger.getLogger(WeatherQueryServiceImpl.class.getName());
	private final AirportDataRepository airportRepository;

	@Inject
	public WeatherQueryServiceImpl(AirportDataRepository repo) {
		this.airportRepository = repo;
		init();
	}

	@Override
	public String ping() {
		return "success" + this.airportRepository.getAirports();
	}

	@Override
	public String weather(String iata, String radiusString) {
		return "weather data";
	}

	public void init() {
		addAirport("EWR", 40.6925, -74.168667);
		addAirport("JFK", 40.639751, -73.778925);
		addAirport("LGA", 40.777245, -73.872608);
		addAirport("MMU", 40.79935, -74.4148747);
	}

	public void addAirport(String iataCode, double latitude, double longitude) {
		AirportData ad = new AirportData();
		ad.setIata(iataCode);
		ad.setLatitude(latitude);
		ad.setLongitude(longitude);
		this.airportRepository.addAirport(ad);
	}
}

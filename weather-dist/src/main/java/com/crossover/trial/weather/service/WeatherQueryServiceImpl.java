package com.crossover.trial.weather.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.repository.AirportDataRepository;
import com.crossover.trial.weather.repository.AtmosphericDataRepository;
import com.crossover.trial.weather.repository.FrequencyDataRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class WeatherQueryServiceImpl implements WeatherQueryService {

	private static final Logger LOGGER = Logger.getLogger(WeatherQueryServiceImpl.class.getName());
	public static final Gson gson = new Gson();

	/** earth radius in KM */
	public static final double R = 6372.8;

	private final AirportDataRepository airportRepository;
	private final AtmosphericDataRepository atmosphericDataRepository;
	private final FrequencyDataRepository frequencyDataRepository;

	@Inject
	public WeatherQueryServiceImpl(AirportDataRepository repo, AtmosphericDataRepository repoAtmospheric,
			FrequencyDataRepository repoFrequencyData) {
		this.airportRepository = repo;
		this.atmosphericDataRepository = repoAtmospheric;
		this.frequencyDataRepository = repoFrequencyData;
		//init();
	}

	@Override
	public String ping() {
		Map<String, Object> retval = new HashMap<>();

		int datasize = 0;
		for (AtmosphericInformation ai : this.atmosphericDataRepository.getAtmosphericInformation().values()) {
			// we only count recent readings
			if (ai.getCloudCover() != null || ai.getHumidity() != null || ai.getPressure() != null
					|| ai.getPrecipitation() != null || ai.getTemperature() != null || ai.getWind() != null) {
				// updated in the last day
				if (ai.getLastUpdateTime() > System.currentTimeMillis() - 86400000) {
					datasize++;
				}
			}
		}
		retval.put("datasize", datasize);

		Map<String, String> freq = new HashMap<>();
		// fraction of queries
		for (AirportData data : this.airportRepository.getAirportDataList()) {
			double frac = (double) this.frequencyDataRepository.getRequestFrequency(data)
					/ this.frequencyDataRepository.getRequestFrequencySize();
			freq.put(data.getIata(), Double.toString(frac));
		}
		retval.put("iata_freq", freq);

		int m = this.frequencyDataRepository.getRadiusFreq().keySet().stream().max(Double::compare).orElse(1000.0)
				.intValue() + 1;

		int[] hist = new int[m];
		for (Map.Entry<Double, Integer> e : this.frequencyDataRepository.getRadiusFreq().entrySet()) {
			int i = e.getKey().intValue() % 10;
			hist[i] += e.getValue();
		}
		retval.put("radius_freq", hist);

		Gson gson1 = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
		return gson1.toJson(retval);
	}

	@Override
	public String weather(String iata, String radiusString) {
		double radius = radiusString == null || radiusString.trim().isEmpty() ? 0 : Double.valueOf(radiusString);
		updateRequestFrequency(iata, radius);

		List<AtmosphericInformation> retval = new ArrayList<>();
		if (radius == 0) {
			int idx = this.airportRepository.getAirportDataIdx(iata);
			retval.add(this.atmosphericDataRepository.getAtmosphericInformation(idx));
		} else {
			AirportData ad = this.airportRepository.getAirport(iata);
			for (int i = 0; i < this.airportRepository.getAirportDataList().size(); i++) {
				if (calculateDistance(ad, this.airportRepository.getAirportDataList().get(i)) <= radius) {
					AtmosphericInformation ai = this.atmosphericDataRepository.getAtmosphericInformation().get(i);
					if (ai != null) {
						if (ai.getCloudCover() != null || ai.getHumidity() != null || ai.getPrecipitation() != null
								|| ai.getPressure() != null || ai.getTemperature() != null || ai.getWind() != null) {
							retval.add(ai);
						}
					}
				}
			}
		}
		return gson.toJson(retval);
	}

	private void updateRequestFrequency(String iata, Double radius) {
		AirportData airportData = this.airportRepository.getAirport(iata);
		this.frequencyDataRepository.addRequestFrequency(airportData);
		this.frequencyDataRepository.addRadiusFrequency(radius);
	}

	private double calculateDistance(AirportData ad1, AirportData ad2) {
		double deltaLat = Math.toRadians(ad2.getLatitude() - ad1.getLatitude());
		double deltaLon = Math.toRadians(ad2.getLongitude() - ad1.getLongitude());
		double a = Math.pow(Math.sin(deltaLat / 2), 2)
				+ Math.pow(Math.sin(deltaLon / 2), 2) * Math.cos(ad1.getLatitude()) * Math.cos(ad2.getLatitude());
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}

	private void init() {
		addAirport("EWR", 40.6925, -74.168667);
		addAirport("JFK", 40.639751, -73.778925);
		addAirport("LGA", 40.777245, -73.872608);
		addAirport("MMU", 40.79935, -74.4148747);
	}

	private void addAirport(String iataCode, double latitude, double longitude) {
		AirportData ad = new AirportData();
		ad.setIata(iataCode);
		ad.setLatitude(latitude);
		ad.setLongitude(longitude);
		this.airportRepository.addAirport(ad);
	}
}

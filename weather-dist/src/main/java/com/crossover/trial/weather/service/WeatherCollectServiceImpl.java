package com.crossover.trial.weather.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.crossover.trial.weather.exception.WeatherException;
import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;
import com.crossover.trial.weather.model.pointtype.AbstractDataPointType;
import com.crossover.trial.weather.model.pointtype.AbstractDataPointTypeFactory;
import com.crossover.trial.weather.repository.AirportDataRepository;
import com.crossover.trial.weather.repository.AtmosphericDataRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * The Class WeatherCollectServiceImpl.
 */
public class WeatherCollectServiceImpl implements WeatherCollectService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(WeatherCollectServiceImpl.class.getName());
	
	/** The Constant gson. */
	public static final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();

	/** The airport repository. */
	private final AirportDataRepository airportRepository;
	
	/** The atmospheric data repository. */
	private final AtmosphericDataRepository atmosphericDataRepository;

	/**
	 * Instantiates a new weather collect service impl.
	 *
	 * @param repo the repo
	 * @param repoAtmospheric the repo atmospheric
	 */
	@Inject
	public WeatherCollectServiceImpl(AirportDataRepository repo, AtmosphericDataRepository repoAtmospheric) {
		this.airportRepository = repo;
		this.atmosphericDataRepository = repoAtmospheric;
		LOGGER.info(WeatherCollectServiceImpl.class.getName() + " initialized.");
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.service.WeatherCollectService#updateWeather(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateWeather(String iataCode, String pointType, String datapointJson) {
		int airportDataIdx = this.airportRepository.getAirportDataIdx(iataCode);
		AtmosphericInformation atmosphericInformation = this.atmosphericDataRepository
				.getAtmosphericInformation(airportDataIdx);
		updateAtmosphericInformation(atmosphericInformation, pointType, datapointJson);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.service.WeatherCollectService#getAirports()
	 */
	@Override
	public String getAirports() {
		String retVal = this.airportRepository.getAirports();
		return gson.toJson(retVal);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.service.WeatherCollectService#getAirport(java.lang.String)
	 */
	@Override
	public String getAirport(String iata) {
		AirportData airport = this.airportRepository.getAirport(iata);
		return gson.toJson(airport);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.service.WeatherCollectService#addAirport(java.lang.String, java.lang.Double, java.lang.Double, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addAirport(String iata, Double latitude, Double longtitude, String city, String country, String icao,
			Double altitude, Double timezone, String dst, String name) {
		AirportData airport = new AirportData();
		airport.setIata(iata);
		airport.setLatitude(latitude);
		airport.setLongitude(longtitude);
		airport.setCity(city);
		airport.setCountry(country);
		airport.setIcao(icao);
		airport.setAltitude(altitude);
		airport.setTimezone(timezone);
		airport.setDst(dst);
		airport.setName(name);
		this.airportRepository.addAirport(airport);
		int airportDataIdx = this.airportRepository.getAirportDataIdx(iata);
		this.atmosphericDataRepository.addAtmosphericInformation(airportDataIdx, new AtmosphericInformation());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.service.WeatherCollectService#deleteAirport(java.lang.String)
	 */
	@Override
	public boolean deleteAirport(String iata) {
		this.airportRepository.deleteAirport(iata);
		return true;
	}

	/**
	 * Update atmospheric information.
	 *
	 * @param atmosphericInformation the atmospheric information
	 * @param pointType the point type
	 * @param datapointJson the datapoint json
	 */
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

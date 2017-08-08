/*
 * 
 */
package com.crossover.trial.weather.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import com.crossover.trial.weather.exception.AirportAlreadyExists;
import com.crossover.trial.weather.exception.AirportNotFound;
import com.crossover.trial.weather.model.AirportData;


/**
 * The Class AirportDataRepositoryImpl.
 */
@Singleton
public class AirportDataRepositoryImpl implements AirportDataRepository {

	/** The Constant airportList. */
	private static final List<AirportData> airportList = new ArrayList<>();

	/**
	 * Instantiates a new airport data repository impl.
	 */
	public AirportDataRepositoryImpl() {
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AirportDataRepository#addAirport(com.crossover.trial.weather.model.AirportData)
	 */
	@Override
	public void addAirport(AirportData airportData) {
		for (AirportData data : AirportDataRepositoryImpl.airportList) {
			if (data.getIata() == airportData.getIata()) {
				throw new AirportAlreadyExists();
			}
		}
		AirportDataRepositoryImpl.airportList.add(airportData);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AirportDataRepository#getAirports()
	 */
	@Override
	public String getAirports() {
		if (AirportDataRepositoryImpl.airportList.isEmpty())
			throw new AirportNotFound();

		Set<String> retval = new HashSet<>();
		for (AirportData ad : AirportDataRepositoryImpl.airportList) {
			retval.add(ad.getIata());
		}
		return retval.toString();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AirportDataRepository#getAirport(java.lang.String)
	 */
	@Override
	public AirportData getAirport(String iata) {
		for (AirportData data : AirportDataRepositoryImpl.airportList) {
			if (data.getIata().equals(iata)) {
				return data;
			}
		}
		throw new AirportNotFound();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AirportDataRepository#deleteAirport(java.lang.String)
	 */
	@Override
	public void deleteAirport(String iata) {
		for (AirportData data : AirportDataRepositoryImpl.airportList) {
			if (data.getIata().equals(iata)) {
				AirportDataRepositoryImpl.airportList.remove(data);
				return;
			}
		}
		throw new AirportNotFound();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AirportDataRepository#getAirportDataIdx(java.lang.String)
	 */
	@Override
	public int getAirportDataIdx(String iataCode) {
		AirportData ad = getAirport(iataCode);
		return AirportDataRepositoryImpl.airportList.indexOf(ad);
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.repository.AirportDataRepository#getAirportDataList()
	 */
	@Override
	public List<AirportData> getAirportDataList() {
		return AirportDataRepositoryImpl.airportList;
	}
}

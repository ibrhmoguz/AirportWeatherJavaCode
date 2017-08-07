package com.crossover.trial.weather.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

import com.crossover.trial.weather.exception.AirportAlreadyExists;
import com.crossover.trial.weather.exception.AirportNotFound;
import com.crossover.trial.weather.model.AirportData;

@Singleton
public class AirportDataRepositoryImpl implements AirportDataRepository {

	private static final List<AirportData> airportList = new ArrayList<>();

	public AirportDataRepositoryImpl() {
	}

	@Override
	public void addAirport(AirportData airportData) {
		for (AirportData data : AirportDataRepositoryImpl.airportList) {
			if (data.getIata() == airportData.getIata()) {
				throw new AirportAlreadyExists();
			}
		}
		AirportDataRepositoryImpl.airportList.add(airportData);
	}

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

	@Override
	public AirportData getAirport(String iata) {
		for (AirportData data : AirportDataRepositoryImpl.airportList) {
			if (data.getIata().equals(iata)) {
				return data;
			}
		}
		throw new AirportNotFound();
	}

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

	@Override
	public int getAirportDataIdx(String iataCode) {
		AirportData ad = getAirport(iataCode);
		return AirportDataRepositoryImpl.airportList.indexOf(ad);
	}

	@Override
	public List<AirportData> getAirportDataList() {
		return AirportDataRepositoryImpl.airportList;
	}
}

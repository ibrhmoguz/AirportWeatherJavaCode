package com.crossover.trial.weather.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import com.crossover.trial.weather.exception.AirportAlreadyExists;
import com.crossover.trial.weather.exception.AirportNotFound;
import com.crossover.trial.weather.model.AirportData;

@ApplicationScoped
public class AirportDataRepository {

	private List<AirportData> airportList;

	public AirportDataRepository() {
		airportList = new ArrayList<>();
	}

	public void AddAirportData(AirportData airportData) {
		for (AirportData data : this.airportList) {
			if (data.getIata() == airportData.getIata()) {
				throw new AirportAlreadyExists();
			}
		}
		this.airportList.add(airportData);
	}

	public String getAirports() {
		if (this.airportList.isEmpty())
			throw new AirportNotFound();

		Set<String> retval = new HashSet<>();
		for (AirportData ad : this.airportList) {
			retval.add(ad.getIata());
		}
		return retval.toString();
	}

	public AirportData getAirport(String iata) {
		for (AirportData data : this.airportList) {
			if (data.getIata().equals(iata)) {
				return data;
			}
		}
		throw new AirportNotFound();
	}

	public void deleteAirport(String iata) {
		for (AirportData data : this.airportList) {
			if (data.getIata().equals(iata)) {
				this.airportList.remove(data);
				return;
			}
		}
		throw new AirportNotFound();
	}

	public int getAirportDataIdx(String iataCode) {
		AirportData ad = getAirport(iataCode);
		return this.airportList.indexOf(ad);
	}
}

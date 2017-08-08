package com.crossover.trial.weather.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crossover.trial.weather.exception.AirportAlreadyExists;
import com.crossover.trial.weather.exception.AirportNotFound;
import com.crossover.trial.weather.exception.WeatherException;
import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;
import com.crossover.trial.weather.repository.AirportDataRepository;
import com.crossover.trial.weather.repository.AtmosphericDataRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import junit.framework.AssertionFailedError;

public class WeatherCollectServiceTest {

	public static final Gson gson = new Gson();

	@InjectMocks
	WeatherCollectServiceImpl collectService;

	@Mock
	AirportDataRepository airportRepository;

	@Mock
	AtmosphericDataRepository atmosphericDataRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(airportRepository.getAirports()).thenReturn("[\"BOS\", \"LHR\"]");
		when(airportRepository.getAirport("BOS")).thenReturn(addAirport("BOS", 42.364347, -71.005181, "Boston",
				"United States", "KBOS", 19.0, -5.0, "A", "General Edward Lawrence Logan Intl"));
		when(atmosphericDataRepository.getAtmosphericInformation()).thenReturn(getAtmosphericDataList());
		when(airportRepository.getAirportDataIdx("BOS")).thenReturn(0);
		when(atmosphericDataRepository.getAtmosphericInformation(0)).thenReturn(getAtmosphericInformation());
	}

	@Test
	public void testUpdateWeather() {
		DataPoint dp = new DataPoint.Builder().withCount(10).withFirst(10).withMedian(20).withLast(30).withMean(22)
				.build();
		try {
			this.collectService.updateWeather("BOS", "CLOUDCOVER", gson.toJson(dp));
			AtmosphericInformation atmospheric = this.atmosphericDataRepository.getAtmosphericInformation(0);
			assertNotNull(atmospheric.getCloudCover());
			assertTrue(atmospheric.getCloudCover().getMean() == 22.0);
		} catch (Exception e) {
			throw new AssertionFailedError("The weather exception should not be thrown!");
		}
	}

	public void testUpdateWeatherWrongPointType() {
		DataPoint dp = new DataPoint.Builder().withCount(10).withFirst(10).withMedian(20).withLast(30).withMean(22)
				.build();
		try {
			this.collectService.updateWeather("LHR", "XXX", gson.toJson(dp));
			throw new AssertionFailedError();

		} catch (Exception e) {
			assertEquals(1, e.getClass().getName().equals(WeatherException.class));
		}
	}

	@Test
	public void testgetAirports() {
		String airpotsJson = this.collectService.getAirports();
		JsonElement airportResult = new JsonParser().parse(airpotsJson);
		assertEquals("[\"BOS\", \"LHR\"]", airportResult.getAsString());
	}

	@Test
	public void testgetAirportsNotFound() {
		assertEquals(0, this.airportRepository.getAirportDataList().size());
		try {
			this.collectService.getAirports();

		} catch (Exception e) {
			assertEquals(1, e.getClass().getName().equals(AirportNotFound.class));
		}
	}

	@Test
	public void testgetAirport() {
		String airpotsJson = this.collectService.getAirport("BOS");
		JsonElement airportResult = new JsonParser().parse(airpotsJson);
		assertEquals("\"BOS\"", airportResult.getAsJsonObject().get("iata").toString());
	}

	@Test
	public void addAirport() {
		this.collectService.addAirport("BOS", 42.364347, -71.005181, "Boston", "United States", "KBOS", 19.0, -5.0, "A",
				"General Edward Lawrence Logan Intl");
		AirportData airport = this.airportRepository.getAirport("BOS");
		assertNotNull(airport);
		assertEquals("General Edward Lawrence Logan Intl", airport.getName());
	}

	@Test
	public void deleteAirport() {
		assertEquals(0, this.airportRepository.getAirportDataList().size());
		addAirport();
		this.collectService.deleteAirport("BOS");
		assertEquals(0, this.airportRepository.getAirportDataList().size());
	}

	private AtmosphericInformation getAtmosphericInformation() {
		DataPoint dp = new DataPoint.Builder().withCount(10).withFirst(10).withMedian(20).withLast(30).withMean(22)
				.build();
		AtmosphericInformation atmospheric = new AtmosphericInformation();
		atmospheric.setWind(dp);
		atmospheric.setCloudCover(dp);
		atmospheric.setHumidity(dp);
		atmospheric.setTemperature(dp);
		atmospheric.setPrecipitation(dp);
		atmospheric.setPressure(dp);
		atmospheric.setLastUpdateTime(System.currentTimeMillis());
		return atmospheric;
	}

	private Map<Integer, AtmosphericInformation> getAtmosphericDataList() {
		Map<Integer, AtmosphericInformation> atmosphericDataList = new HashMap<>();
		DataPoint dp = new DataPoint.Builder().withCount(10).withFirst(10).withMedian(20).withLast(30).withMean(22)
				.build();
		AtmosphericInformation atmospheric = new AtmosphericInformation();
		atmospheric.setWind(dp);
		atmospheric.setCloudCover(dp);
		atmospheric.setHumidity(dp);
		atmospheric.setTemperature(dp);
		atmospheric.setPrecipitation(dp);
		atmospheric.setPressure(dp);
		atmospheric.setLastUpdateTime(System.currentTimeMillis());
		atmosphericDataList.put(0, atmospheric);
		return atmosphericDataList;
	}

	private List<AirportData> getAirpotList() {
		List<AirportData> airportList = new ArrayList<>();
		airportList.add(addAirport("BOS", 42.364347, -71.005181, "Boston", "United States", "KBOS", 19.0, -5.0, "A",
				"General Edward Lawrence Logan Intl"));
		airportList.add(
				addAirport("LHR", 51.4775, -0.461389, "London", "United Kingdom", "EGLL", 83.0, 0.0, "E", "Heathrow"));

		return airportList;
	}

	private AirportData addAirport(String iata, Double latitude, Double longtitude, String city, String country,
			String icao, Double altitude, Double timezone, String dst, String name) {
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
		return airport;
	}
}

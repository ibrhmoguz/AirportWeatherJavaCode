package com.crossover.trial.weather.service;

import static org.junit.Assert.assertEquals;
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

import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;
import com.crossover.trial.weather.repository.AirportDataRepository;
import com.crossover.trial.weather.repository.AtmosphericDataRepository;
import com.crossover.trial.weather.repository.FrequencyDataRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * The Class WeatherQueryServiceTest.
 */
public class WeatherQueryServiceTest {

	/** The query service. */
	@InjectMocks
	WeatherQueryServiceImpl queryService;

	/** The airport repository. */
	@Mock
	AirportDataRepository airportRepository;

	/** The atmospheric data repository. */
	@Mock
	AtmosphericDataRepository atmosphericDataRepository;

	/** The frequency data repository. */
	@Mock
	FrequencyDataRepository frequencyDataRepository;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(airportRepository.getAirportDataList()).thenReturn(getAirpotList());
		when(atmosphericDataRepository.getAtmosphericInformation()).thenReturn(getAtmosphericDataList());
	}
	
	/**
	 * Test ping.
	 */
	@Test
	public void testPing() {
		String ping = this.queryService.ping();
		JsonElement pingResult = new JsonParser().parse(ping);
		assertEquals(1, pingResult.getAsJsonObject().get("datasize").getAsInt());
		assertEquals(2, pingResult.getAsJsonObject().get("iata_freq").getAsJsonObject().entrySet().size());
	}
	
	/**
	 * Test weather.
	 */
	@Test
	public void testWeather() {
		String result = this.queryService.weather("BOS", "20");
		JsonElement weatherResult = new JsonParser().parse(result);
		assertEquals(1, weatherResult.getAsJsonArray().size());
	}

	/**
	 * Gets the atmospheric data list.
	 *
	 * @return the atmospheric data list
	 */
	private Map<Integer, AtmosphericInformation> getAtmosphericDataList() {
		Map<Integer, AtmosphericInformation> atmosphericDataList = new HashMap<>();
		DataPoint dp = new DataPoint.Builder().withCount(10).withFirst(10).withMedian(20).withLast(30).withMean(22).build();
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

	/**
	 * Gets the airpot list.
	 *
	 * @return the airpot list
	 */
	private List<AirportData> getAirpotList() {
		List<AirportData> airportList = new ArrayList<>();
		airportList.add(addAirport("BOS", 42.364347, -71.005181, "Boston", "United States", "KBOS", 19.0, -5.0, "A",
				"General Edward Lawrence Logan Intl"));
		airportList.add(
				addAirport("LHR", 51.4775, -0.461389, "London", "United Kingdom", "EGLL", 83.0, 0.0, "E", "Heathrow"));

		return airportList;
	}

	/**
	 * Adds the airport.
	 *
	 * @param iata the iata
	 * @param latitude the latitude
	 * @param longtitude the longtitude
	 * @param city the city
	 * @param country the country
	 * @param icao the icao
	 * @param altitude the altitude
	 * @param timezone the timezone
	 * @param dst the dst
	 * @param name the name
	 * @return the airport data
	 */
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

package com.crossover.trial.weather.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.service.WeatherCollectService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class RestWeatherQueryEndpointTest {

	@Mock
	private WeatherQueryEndpoint queryRestService;

	@Mock
	private WeatherCollectService collectService;

	public RestWeatherQueryEndpointTest() {
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		init();
	}
/*
	@Test
	public void testPing() {
		String ping = this.queryRestService.ping();
		JsonElement pingResult = new JsonParser().parse(ping);
		assertEquals(1, pingResult.getAsJsonObject().get("datasize").getAsInt());
		assertEquals(4, pingResult.getAsJsonObject().get("iata_freq").getAsJsonObject().entrySet().size());
	}
*/
	/*
	 * @Test public void testWeather() { Response response =
	 * this.queryRestService.weather("JFK", "20"); JsonElement pingResult = new
	 * JsonParser().parse(ping); assertEquals(1,
	 * pingResult.getAsJsonObject().get("datasize").getAsInt()); assertEquals(4,
	 * pingResult.getAsJsonObject().get("iata_freq").getAsJsonObject().entrySet(
	 * ).size()); }
	 */

	private void init() {
		this.collectService.addAirport("BOS", 42.364347, -71.005181, "Boston", "United States", "KBOS", 19.0, -5.0, "A",
				"General Edward Lawrence Logan Intl");
		// addAirport("EWR", 40.6925, -74.168667);
		// addAirport("JFK", 40.639751, -73.778925);
		// addAirport("LGA", 40.777245, -73.872608);
		// addAirport("MMU", 40.79935, -74.4148747);
	}

	private void addAirport(String iataCode, double latitude, double longitude) {
		AirportData ad = new AirportData();
		ad.setIata(iataCode);
		ad.setLatitude(latitude);
		ad.setLongitude(longitude);

	}
}

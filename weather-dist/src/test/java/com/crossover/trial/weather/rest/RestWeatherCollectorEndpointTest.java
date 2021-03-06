package com.crossover.trial.weather.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crossover.trial.weather.service.WeatherCollectService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * The Class RestWeatherCollectorEndpointTest.
 */
public class RestWeatherCollectorEndpointTest {

	/** The collect rest service. */
	@InjectMocks
	RestWeatherCollectorEndpoint collectRestService;

	/** The collect service. */
	@Mock
	private WeatherCollectService collectService;

	/**
	 * Instantiates a new rest weather collector endpoint test.
	 */
	public RestWeatherCollectorEndpointTest() {
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(collectService.deleteAirport("BOS")).thenReturn(true);
		when(collectService.addAirport("BOS", 12.0, 13.0, null, null, null, 345.0, 24.0, null, null)).thenReturn(true);
		when(collectService.updateWeather("BOS", "WIND", "JSON")).thenReturn(true);
		when(collectService.getAirports()).thenReturn("\"[EWR, LCY, JFK]\"");
		when(collectService.getAirport("BOS")).thenReturn(
				"{    \"iata\": \"BOS\",    \"latitude\": 42.364347,    \"longitude\": 42.364347,    \"city\": \"Boston\",    \"country\": \"United States\",    \"icao\": \"KBOS\",    \"altitude\": 19,    \"timezone\": -5,    \"dst\": \"A\",    \"name\": \"General Edward Lawrence Logan Intl\"}");
	}

	/**
	 * Test ping.
	 */
	@Test
	public void testPing() {
		Response response = this.collectRestService.ping();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Test update weather.
	 */
	@Test
	public void testUpdateWeather() {
		Response response = this.collectRestService.updateWeather("BOS", "WIND", "JSON");
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Test update weather bas request.
	 */
	@Test
	public void testUpdateWeatherBasRequest() {
		Response response = this.collectRestService.updateWeather(null, null, null);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	/**
	 * Test get airports.
	 */
	@Test
	public void testGetAirports() {
		Response response = this.collectRestService.getAirports();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("\"[EWR, LCY, JFK]\"", response.getEntity().toString());
	}

	/**
	 * Test get airport.
	 */
	@Test
	public void testGetAirport() {
		Response response = this.collectRestService.getAirport("BOS");
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		JsonElement airportResult = new JsonParser().parse(response.getEntity().toString());
		assertEquals("\"Boston\"", airportResult.getAsJsonObject().get("city").toString());
	}

	/**
	 * Test add airport.
	 */
	@Test
	public void testAddAirport() {
		Response response = this.collectRestService.addAirport("BOS", "12.0", "13.0", null, null, null, "345.0", "24.0",
				null, null);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Test add airport bad request.
	 */
	@Test
	public void testAddAirportBadRequest() {
		Response response = this.collectRestService.addAirport("BOS", "12.0", "", null, null, null, "345.0", "24.0",
				null, null);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	/**
	 * Test add airport wrong number format.
	 */
	@Test
	public void testAddAirportWrongNumberFormat() {
		Response response = this.collectRestService.addAirport("BOS", "12.0", "13.0", null, null, null, "XXX", "24.0",
				null, null);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	/**
	 * Test delete airport.
	 */
	@Test
	public void testDeleteAirport() {
		Response response = this.collectRestService.deleteAirport("BOS");
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	/**
	 * Test delete airport bad request.
	 */
	@Test
	public void testDeleteAirportBadRequest() {
		Response response = this.collectRestService.deleteAirport(null);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
}

package com.crossover.trial.weather.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crossover.trial.weather.service.WeatherQueryService;


/**
 * The Class RestWeatherQueryEndpointTest.
 */
public class RestWeatherQueryEndpointTest {

	/** The query rest service. */
	@InjectMocks
	private RestWeatherQueryEndpoint queryRestService;

	/** The query service. */
	@Mock
	private WeatherQueryService queryService;

	/**
	 * Instantiates a new rest weather query endpoint test.
	 */
	public RestWeatherQueryEndpointTest() {
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(queryService.ping()).thenReturn("ready");
		when(queryService.weather("BOS", "20")).thenReturn("ready");
	}

	/**
	 * Test ping.
	 */
	@Test
	public void testPing() {
		String response = this.queryRestService.ping();
		assertEquals("ready", response);
	}
	
	/**
	 * Test weather.
	 */
	@Test
	public void testWeather() {
		Response response = this.queryRestService.weather("BOS", "20");
		assertEquals(200, response.getStatus());
	}
}

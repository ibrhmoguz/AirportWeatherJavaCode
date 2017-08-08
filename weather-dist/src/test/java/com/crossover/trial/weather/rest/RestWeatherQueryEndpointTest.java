package com.crossover.trial.weather.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.service.WeatherCollectService;
import com.crossover.trial.weather.service.WeatherQueryService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class RestWeatherQueryEndpointTest {

	@InjectMocks
	private RestWeatherQueryEndpoint queryRestService;

	@Mock
	private WeatherQueryService queryService;

	public RestWeatherQueryEndpointTest() {
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(queryService.ping()).thenReturn("ready");
		when(queryService.weather("BOS", "20")).thenReturn("ready");
	}

	@Test
	public void testPing() {
		String response = this.queryRestService.ping();
		assertEquals("ready", response);
	}
	
	@Test
	public void testWeather() {
		Response response = this.queryRestService.weather("BOS", "20");
		assertEquals(200, response.getStatus());
	}
}

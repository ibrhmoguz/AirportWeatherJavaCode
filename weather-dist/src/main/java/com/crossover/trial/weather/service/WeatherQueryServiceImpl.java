package com.crossover.trial.weather.service;

public class WeatherQueryServiceImpl implements WeatherQueryService {

	@Override
	public String ping() {
		return "success";
	}

	@Override
	public String weather(String iata, String radiusString) {
		return "weather data";
	}
}

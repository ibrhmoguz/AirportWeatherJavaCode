package com.crossover.trial.weather.config;

import org.glassfish.jersey.server.ResourceConfig;


/**
 * The Class WeatherConfig registers resources and injected classes.
 * 
 * @author Ibrahim OGUZ
 */
public class WeatherConfig extends ResourceConfig {

	/**
	 * Instantiates a new weather config.
	 */
	public WeatherConfig() {
		super(new WeatherApplication().getClasses());
		register(new WeatherBinder());
		packages(true, "com.crossover.trial.weather.rest");
	}
}

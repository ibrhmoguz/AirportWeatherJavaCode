package com.crossover.trial.weather.service;

/**
 * The interface shared to airport weather collection systems.
 *
 * @author Ibrahim OGUZ
 */
public interface WeatherCollectService {

	 /**
     * A liveliness check for the collection endpoint.
     *
     * @return 1 if the endpoint is alive functioning, 0 otherwise
     */
	String ping();
}

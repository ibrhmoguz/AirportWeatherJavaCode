package com.crossover.trial.weather.exception;


/**
 * An internal exception marker.
 */
public class WeatherException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1763593411470554908L;
	
	/**
	 * Instantiates a new weather exception.
	 *
	 * @param message the message
	 */
	public WeatherException(String message){
		super(message);
	}
}

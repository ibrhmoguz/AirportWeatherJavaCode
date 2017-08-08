package com.crossover.trial.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


/**
 * The Class WeatherExceptionHandler.
 */
public class WeatherExceptionHandler implements ExceptionMapper<WeatherException> {
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(WeatherException ex) {
		return Response.status(Response.Status.NOT_MODIFIED).entity(ex.getMessage()).build();
	}
}

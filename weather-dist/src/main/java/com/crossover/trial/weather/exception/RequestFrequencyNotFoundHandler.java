package com.crossover.trial.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * The Class RequestFrequencyNotFoundHandler.
 */
@Provider
public class RequestFrequencyNotFoundHandler implements ExceptionMapper<RadiusFrequencyNotFound> {
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(RadiusFrequencyNotFound ex) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}

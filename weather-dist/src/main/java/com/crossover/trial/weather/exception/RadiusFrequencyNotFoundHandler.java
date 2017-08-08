package com.crossover.trial.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * The Class RadiusFrequencyNotFoundHandler.
 */
@Provider
public class RadiusFrequencyNotFoundHandler implements ExceptionMapper<RequestFrequencyNotFound> {
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(RequestFrequencyNotFound ex) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}

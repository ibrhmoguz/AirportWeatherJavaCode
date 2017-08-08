package com.crossover.trial.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * The Class AtmosphericInformationNotFoundHandler.
 */
@Provider
public class AtmosphericInformationNotFoundHandler implements ExceptionMapper<AtmosphericInformationNotFound> {
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(AtmosphericInformationNotFound ex) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}

package com.crossover.trial.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


/**
 * The Class AirportAlreadyExistsHandler.
 */
public class AirportAlreadyExistsHandler implements ExceptionMapper<AirportAlreadyExists> {
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(AirportAlreadyExists ex) {
		return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
	}
}

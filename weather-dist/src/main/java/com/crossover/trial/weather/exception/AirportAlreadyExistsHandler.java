package com.crossover.trial.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AirportAlreadyExistsHandler implements ExceptionMapper<AirportAlreadyExists> {
	public Response toResponse(AirportAlreadyExists ex) {
		return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
	}
}

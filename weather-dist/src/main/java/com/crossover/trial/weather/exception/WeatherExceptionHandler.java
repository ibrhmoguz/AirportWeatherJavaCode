package com.crossover.trial.weather.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WeatherExceptionHandler implements ExceptionMapper<WeatherException> {
	public Response toResponse(WeatherException ex) {
		return Response.status(Response.Status.NOT_MODIFIED).entity(ex.getMessage()).build();
	}
}

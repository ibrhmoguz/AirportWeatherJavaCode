package com.crossover.trial.weather.rest;

import com.crossover.trial.weather.exception.WeatherException;
import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;
import com.crossover.trial.weather.model.DataPointType;
import com.crossover.trial.weather.service.WeatherCollectService;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static com.crossover.trial.weather.rest.RestWeatherQueryEndpoint.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * A REST implementation of the WeatherCollector API. Accessible only to airport
 * weather collection sites via secure VPN.
 *
 * @author code test administrator
 */

@Path("/collect")
public class RestWeatherCollectorEndpoint implements WeatherCollectorEndpoint {

	@Inject
	private WeatherCollectService collectService;

	@Override
	public Response ping() {
		return Response.status(Response.Status.OK).entity("ready").build();
	}

	@Override
	public Response updateWeather(@PathParam("iata") String iataCode, @PathParam("pointType") String pointType,
			String datapointJson) {
		if (iataCode.isEmpty() || pointType.isEmpty() || datapointJson.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		this.collectService.updateWeather(iataCode, pointType, datapointJson);
		return Response.status(Response.Status.OK).build();
	}

	@Override
	public Response getAirports() {
		String retVal = this.collectService.getAirports();
		return Response.status(Response.Status.OK).entity(retVal).build();
	}

	@Override
	public Response getAirport(@PathParam("iata") String iata) {
		String retVal = this.collectService.getAirport(iata);
		return Response.status(Response.Status.OK).entity(retVal).build();
	}

	@Override
	public Response addAirport(@PathParam("iata") String iata, @PathParam("lat") String latString,
			@PathParam("long") String longString) {
		if (iata.isEmpty() || latString.isEmpty() || longString.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		try {
			double latitude = Double.parseDouble(latString);
			double longtitude = Double.parseDouble(latString);

			this.collectService.addAirport(iata, latitude, longtitude);
			return Response.status(Response.Status.OK).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response deleteAirport(@PathParam("iata") String iata) {
		if (iata.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		this.collectService.deleteAirport(iata);
		return Response.status(Response.Status.OK).build();
	}

	@Override
	public Response exit() {
		System.exit(0);
		return Response.noContent().build();
	}
}

package com.crossover.trial.weather.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crossover.trial.weather.service.WeatherCollectService;


/**
 * A REST implementation of the WeatherCollector API. Accessible only to airport
 * weather collection sites via secure VPN.
 *
 * @author Ibrahim OGUZ
 */
@Path("/collect")
public class RestWeatherCollectorEndpoint implements WeatherCollectorEndpoint {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(RestWeatherCollectorEndpoint.class.getName());
	
	/** The collect service. */
	private WeatherCollectService collectService;

	/**
	 * Instantiates a new rest weather collector endpoint.
	 *
	 * @param service the service
	 */
	@Inject
	public RestWeatherCollectorEndpoint(WeatherCollectService service) {
		this.collectService = service;
		LOGGER.info(RestWeatherCollectorEndpoint.class.getName() + " initialized.");
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherCollectorEndpoint#ping()
	 */
	@Override
	@GET
	@Path("/ping")
	public Response ping() {
		return Response.status(Response.Status.OK).entity("ready").build();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherCollectorEndpoint#updateWeather(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@POST
	@Path("/weather/{iata}/{pointType}/{datapointJson}")
	public Response updateWeather(@PathParam("iata") String iataCode, @PathParam("pointType") String pointType,
			@PathParam("datapointJson") String datapointJson) {
		if (iataCode == null || iataCode.isEmpty() || pointType == null || pointType.isEmpty() || datapointJson == null
				|| datapointJson.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		this.collectService.updateWeather(iataCode, pointType, datapointJson);
		return Response.status(Response.Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherCollectorEndpoint#getAirports()
	 */
	@Override
	@GET
	@Path("/airports")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAirports() {
		String retVal = this.collectService.getAirports();
		return Response.status(Response.Status.OK).entity(retVal).build();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherCollectorEndpoint#getAirport(java.lang.String)
	 */
	@Override
	@GET
	@Path("/airport/{iata}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAirport(@PathParam("iata") String iata) {
		String retVal = this.collectService.getAirport(iata);
		return Response.status(Response.Status.OK).entity(retVal).build();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherCollectorEndpoint#addAirport(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@POST
	@Path("/airport/{iata}/{lat}/{long}/{city}/{country}/{icao}/{altitude}/{timezone}/{dst}/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAirport(@PathParam("iata") String iata, @PathParam("lat") String latString,
			@PathParam("long") String longString, @PathParam("city") String city, @PathParam("country") String country,
			@PathParam("icao") String icao, @PathParam("altitude") String altitudeString,
			@PathParam("timezone") String timezoneString, @PathParam("dst") String dst,
			@PathParam("name") String name) {
		if (iata.isEmpty() || latString.isEmpty() || longString.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		try {
			double latitude = Double.parseDouble(latString);
			double longtitude = Double.parseDouble(latString);
			double altitude = Double.parseDouble(altitudeString);
			double timezone = Double.parseDouble(timezoneString);

			this.collectService.addAirport(iata, latitude, longtitude, city, country, icao, altitude, timezone, dst,
					name);
			return Response.status(Response.Status.OK).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherCollectorEndpoint#deleteAirport(java.lang.String)
	 */
	@Override
	@DELETE
	@Path("/airport/{iata}")
	public Response deleteAirport(@PathParam("iata") String iata) {
		if (iata == null || iata.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		this.collectService.deleteAirport(iata);
		return Response.status(Response.Status.OK).build();
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherCollectorEndpoint#exit()
	 */
	@Override
	public Response exit() {
		System.exit(0);
		return Response.noContent().build();
	}
}

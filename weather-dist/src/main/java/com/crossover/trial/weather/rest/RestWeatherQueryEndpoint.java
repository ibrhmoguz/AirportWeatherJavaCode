package com.crossover.trial.weather.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crossover.trial.weather.service.WeatherQueryService;


/**
 * The Weather App REST endpoint allows clients to query, update and check
 * health stats. Currently, all data is held in memory. The end point deploys to
 * a single container
 *
 * @author Ibrahim OGUZ
 */
@Path("/query")
public class RestWeatherQueryEndpoint implements WeatherQueryEndpoint {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(RestWeatherQueryEndpoint.class.getName());
	
	/** The query service. */
	private WeatherQueryService queryService;

	/**
	 * Instantiates a new rest weather query endpoint.
	 *
	 * @param service the service
	 */
	@Inject
	public RestWeatherQueryEndpoint(WeatherQueryService service){
		this.queryService = service;
		LOGGER.info(RestWeatherQueryEndpoint.class.getName() + " initialized.");
	}

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.rest.WeatherQueryEndpoint#ping()
	 */
	@Override
	@GET
	@Path("/ping")
	@Produces(MediaType.APPLICATION_JSON)
	public String ping() {
		return this.queryService.ping();
	}

	/**
	 * Given a query in json format {'iata': CODE, 'radius': km} extracts the
	 * requested airport information and return a list of matching atmosphere
	 * information.
	 *
	 * @param iata
	 *            the iataCode
	 * @param radiusString
	 *            the radius in km
	 *
	 * @return a list of atmospheric information
	 */
	@Override
	@GET
	@Path("/weather/{iata}/{radius}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response weather(@PathParam("iata") String iata, @PathParam("radius") String radiusString) {
		String retVal = this.queryService.weather(iata, radiusString);
		return Response.status(Response.Status.OK).entity(retVal).build();
	}
}

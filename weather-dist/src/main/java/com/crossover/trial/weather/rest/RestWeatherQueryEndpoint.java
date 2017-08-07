package com.crossover.trial.weather.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.crossover.trial.weather.service.WeatherQueryService;

/**
 * The Weather App REST endpoint allows clients to query, update and check
 * health stats. Currently, all data is held in memory. The end point deploys to
 * a single container
 *
 * @author code test administrator
 */
@Path("/query")
public class RestWeatherQueryEndpoint implements WeatherQueryEndpoint {

	@Inject
	private WeatherQueryService queryService;

	@Override
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
	public Response weather(String iata, String radiusString) {
		String retVal = this.queryService.weather(iata, radiusString);
		return Response.status(Response.Status.OK).entity(retVal).build();
	}
}

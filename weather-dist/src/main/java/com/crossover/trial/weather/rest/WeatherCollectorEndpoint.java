/*
 * 
 */
package com.crossover.trial.weather.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.crossover.trial.weather.exception.WeatherException;
import com.crossover.trial.weather.model.AirportData;
import com.crossover.trial.weather.model.DataPointType;


/**
 * The interface shared to airport weather collection systems.
 *
 * @author code test administartor
 */
public interface WeatherCollectorEndpoint {

	/**
	 * A liveliness check for the collection endpoint.
	 *
	 * @return 1 if the endpoint is alive functioning, 0 otherwise
	 */
	Response ping();

	/**
	 * Update the airports atmospheric information for a particular pointType
	 * with json formatted data point information.
	 *
	 * @param iataCode            the 3 letter airport code
	 * @param pointType            the point type, {@link DataPointType} for a complete list
	 * @param datapointJson            a json dict containing mean, first, second, thrid and count
	 *            keys
	 * @return HTTP Response code
	 * @throws WeatherException the weather exception
	 */
	Response updateWeather(@PathParam("iata") String iataCode, @PathParam("pointType") String pointType,
			String datapointJson);

	/**
	 * Return a list of known airports as a json formatted list.
	 *
	 * @return HTTP Response code and a json formatted list of IATA codes
	 */
	Response getAirports();

	/**
	 * Retrieve airport data, including latitude and longitude for a particular
	 * airport.
	 *
	 * @param iata            the 3 letter airport code
	 * @return an HTTP Response with a json representation of
	 *         {@link AirportData}
	 */
	Response getAirport(@PathParam("iata") String iata);

	/**
	 * Add a new airport to the known airport list.
	 *
	 * @param iata            the 3 letter airport code of the new airport
	 * @param latString            the airport's latitude in degrees as a string [-90, 90]
	 * @param longString            the airport's longitude in degrees as a string [-180, 180]
	 * @param city the city
	 * @param country the country
	 * @param icao the icao
	 * @param altitudeString the altitude string
	 * @param timezoneString the timezone string
	 * @param dst the dst
	 * @param name the name
	 * @return HTTP Response code for the add operation
	 */
	Response addAirport(@PathParam("iata") String iata, @PathParam("lat") String latString,
			@PathParam("long") String longString, @PathParam("city") String city, @PathParam("country") String country,
			@PathParam("icao") String icao, @PathParam("altitude") String altitudeString,
			@PathParam("timezone") String timezoneString, @PathParam("dst") String dst, @PathParam("name") String name);

	/**
	 * Remove an airport from the known airport list.
	 *
	 * @param iata            the 3 letter airport code
	 * @return HTTP Response code for the delete operation
	 */
	Response deleteAirport(@PathParam("iata") String iata);

	/**
	 * Exit.
	 *
	 * @return the response
	 */
	/*
	 * Shutdown the host application.
	 * 
	 */
	@GET
	@Path("/exit")
	Response exit();
}

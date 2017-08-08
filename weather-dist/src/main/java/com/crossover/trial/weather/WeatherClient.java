/*
 * 
 */
package com.crossover.trial.weather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.crossover.trial.weather.model.DataPoint;


/**
 * A reference implementation for the weather client. Consumers of the REST API
 * can look at WeatherClient to understand API semantics. This existing client
 * populates the REST endpoint with dummy data useful for testing.
 *
 * @author code test administrator
 */
public class WeatherClient {

	/** The Constant BASE_URI. */
	private static final String BASE_URI = "http://localhost:9090";

	/**  end point for read queries. */
	private WebTarget query;

	/**  end point to supply updates. */
	private WebTarget collect;

	/**
	 * Instantiates a new weather client.
	 */
	public WeatherClient() {
		Client client = ClientBuilder.newClient();
		query = client.target(BASE_URI + "/query");
		collect = client.target(BASE_URI + "/collect");
	}

	/**
	 * Ping collect.
	 */
	public void pingCollect() {
		WebTarget path = collect.path("/ping");
		Response response = path.request().get();
		System.out.print("collect.ping: " + response.readEntity(String.class) + "\n");
	}

	/**
	 * Query.
	 *
	 * @param iata the iata
	 */
	public void query(String iata) {
		WebTarget path = query.path("/weather/" + iata + "/0");
		Response response = path.request().get();
		System.out.println("query." + iata + ".0: " + response.readEntity(String.class));
	}

	/**
	 * Ping query.
	 */
	public void pingQuery() {
		WebTarget path = query.path("/ping");
		Response response = path.request().get();
		System.out.println("query.ping: " + response.readEntity(String.class));
	}

	/**
	 * Populate.
	 *
	 * @param pointType the point type
	 * @param first the first
	 * @param last the last
	 * @param mean the mean
	 * @param median the median
	 * @param count the count
	 */
	public void populate(String pointType, int first, int last, int mean, int median, int count) {
		WebTarget path = collect.path("/weather/BOS/" + pointType);
		DataPoint dp = new DataPoint.Builder().withFirst(first).withLast(last).withMean(mean).withMedian(median)
				.withCount(count).build();
		path.request().post(Entity.entity(dp, "application/json"));
	}

	/**
	 * Exit.
	 */
	public void exit() {
		try {
			collect.path("/exit").request().get();
		} catch (Throwable t) {
			// swallow
		}
	}

	/**
	 * Adds the airport.
	 *
	 * @param iata the iata
	 * @param lat the lat
	 * @param lon the lon
	 */
	public void addAirport(String iata, String lat, String lon) {
		WebTarget path = collect.path("/airport/" + iata + "/" + lat + "/" + lon);
		Response response = path.request().get();
		System.out.print("collect.addAirport: " + response.readEntity(String.class) + "\n");
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		WeatherClient wc = new WeatherClient();
		wc.pingCollect();
		wc.addAirport("BOS", "24.123456", "25.123456");
		wc.populate("wind", 0, 10, 6, 4, 20);

		wc.query("BOS");
		wc.query("JFK");
		wc.query("EWR");
		wc.query("LGA");
		wc.query("MMU");

		wc.pingQuery();
		wc.exit();
		System.out.print("complete");
		System.exit(0);
	}
}

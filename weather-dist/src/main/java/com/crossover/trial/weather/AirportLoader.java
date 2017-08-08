/*
 * 
 */
package com.crossover.trial.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crossover.trial.weather.model.AirportData;


/**
 * A simple airport loader which reads a file from disk and sends entries to the
 * webservice.
 *
 * @author Ibrahim OGUZ
 */
public class AirportLoader {

	/**  end point to supply updates. */
	private WebTarget collect;

	/** The airport list. */
	private List<AirportData> airportList;

	/**
	 * Instantiates a new airport loader.
	 */
	public AirportLoader() {
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:9090/query");
		collect = client.target("http://localhost:9090/collect");
		airportList = new ArrayList<>();
	}

	/**
	 * Upload.
	 *
	 * @param airportDataStream the airport data stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void upload(InputStream airportDataStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(airportDataStream));
		String line = null;
		String[] parts;
		while ((line = reader.readLine()) != null) {
			parts = line.split(",");
			AirportData airport = new AirportData();
			airport.setName(parts[1].substring(1, parts[1].length() - 1));
			airport.setCity(parts[2].substring(1, parts[2].length() - 1));
			airport.setCountry(parts[3].substring(1, parts[3].length() - 1));
			airport.setIata(parts[4].substring(1, parts[4].length() - 1));
			airport.setIcao(parts[5].substring(1, parts[5].length() - 1));
			if (!parts[6].isEmpty()) {
				airport.setLatitude(Double.valueOf(parts[6]));
			}
			if (!parts[7].isEmpty()) {
				airport.setLongitude(Double.valueOf(parts[7]));
			}
			if (!parts[8].isEmpty()) {
				airport.setAltitude(Double.valueOf(parts[8]));
			}
			if (!parts[9].isEmpty()) {
				airport.setTimezone(Double.valueOf(parts[9]));
			}
			if (!parts[10].isEmpty()) {
				airport.setDst(parts[10].substring(1, parts[10].length() - 1));
			}

			airportList.add(airport);
		}
	}

	/**
	 * Send data to service.
	 */
	public void sendDataToService() {
		for (AirportData airport : airportList) {
			addAirport(airport);
		}
	}

	/**
	 * Adds the airport.
	 *
	 * @param airport the airport
	 */
	public void addAirport(AirportData airport) {
		String payload = "\r\n{\r\n\"iata\":\"" + airport.getIata() 
				+ "\",\r\n\"lat\": " + airport.getLatitude()
				+ ",\r\n\"long\": " + airport.getLongitude() 
				+ ",\r\n\"city\": " + airport.getCity()
				+ ",\r\n\"country\": " + airport.getCountry() 
				+ ",\r\n\"icao\": " + airport.getIcao()
				+ ",\r\n\"altitude\": " + airport.getAltitude() 
				+ ",\r\n\"timezone\": " + airport.getTimezone()
				+ ",\r\n\"dst\": " + airport.getDst() 
				+ ",\r\n\"name\": " + airport.getName() + " \r\n}";
		WebTarget path = collect.path("/airport/" + airport.getIata() + "/" 
				+ airport.getLatitude() + "/"
				+ airport.getLongitude() + "/" 
				+ airport.getCity() + "/" 
				+ airport.getCountry() + "/"
				+ airport.getIcao()+ "/" 
				+ airport.getAltitude() + "/" 
				+ airport.getTimezone() + "/" 
				+ airport.getDst()+ "/" 
				+ airport.getName());
		Response response = path.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(payload, MediaType.APPLICATION_JSON), Response.class);
		System.out.print("collect.addAirport: " + airport.getIata() + ". Status: " + response.getStatus() + "\n");
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) throws IOException {
		File airportDataFile = new File("src/main/resources/airports.dat");
		if (!airportDataFile.exists() || airportDataFile.length() == 0) {
			System.err.println(airportDataFile + " is not a valid input");
			System.exit(1);
		}

		AirportLoader al = new AirportLoader();
		al.upload(new FileInputStream(airportDataFile));
		al.sendDataToService();
		System.exit(0);
	}
}

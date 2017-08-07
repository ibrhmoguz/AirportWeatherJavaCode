package com.crossover.trial.weather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crossover.trial.weather.model.AirportData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple airport loader which reads a file from disk and sends entries to the
 * webservice
 * 
 * @author Ibrahim OGUZ
 */
public class AirportLoader {

	/** end point for read queries */
	private WebTarget query;

	/** end point to supply updates */
	private WebTarget collect;

	private List<AirportData> airportList;

	public AirportLoader() {
		Client client = ClientBuilder.newClient();
		query = client.target("http://localhost:9090/query");
		collect = client.target("http://localhost:9090/collect");
		airportList = new ArrayList<>();
	}

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

	public void sendDataToService() {
		for (AirportData airport : airportList) {
			addAirport(airport);
		}
	}

	public void addAirport(AirportData airport) {
		String payload = "\r\n{\r\n\"iata\":\"" + airport.getIata() + "\",\r\n\"lat\": " + airport.getLatitude()
				+ ",\r\n\"long\": " + airport.getLongitude() + " \r\n}";
		WebTarget path = collect
				.path("/airport/" + airport.getIata() + "/" + airport.getLatitude() + "/" + airport.getLongitude());
		Response response = path.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(payload, MediaType.APPLICATION_JSON), Response.class);
		System.out.print("collect.addAirport: " + response.readEntity(String.class) + "\n");
	}

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

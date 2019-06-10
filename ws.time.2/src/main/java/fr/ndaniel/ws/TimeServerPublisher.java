package fr.ndaniel.ws;

import javax.xml.ws.Endpoint;

public class TimeServerPublisher {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:9090/time", new TimeServerImpl());
	}

}

package server;

import javax.xml.ws.Endpoint;

public class TimeServerPublisher {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:9080/time", new TimeServerImpl());
	}

}

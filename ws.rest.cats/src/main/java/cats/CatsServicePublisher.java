package cats;

import java.io.IOException;

import javax.xml.ws.Endpoint;

public class CatsServicePublisher {

	public static void main(String[] args) throws IOException {
		System.out.println("Publishing RESTful Cats Service ...");
		Endpoint.publish("http://localhost:9082/ws/rest/cats", new CatsServiceImpl());
		System.out.println("Service up");
	}

}

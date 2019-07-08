package cats;

import javax.xml.ws.Endpoint;

public class CatsServicePublisher {

	public static void main(String[] args) {
		System.out.println("Publishing RESTful Cats Service ...");
		Endpoint.publish("http://localhost:9082/ws/rest/cats", new CatsServiceImpl());
		System.out.println("Service up");
	}

}

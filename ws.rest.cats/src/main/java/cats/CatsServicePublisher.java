package cats;

import javax.xml.ws.Endpoint;

public class CatsServicePublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9082/ws/rest/cats", new CatsServiceImpl());
	}

}

package time;

import javax.xml.ws.Endpoint;

public class TimeServicePublisher {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:9080/ws/time", new TimeServiceImpl());
	}

}

package fr.ndaniel.ws;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "fr.ndaniel.ws.TimeServer")
public class TimeServerImpl implements TimeServer {

	public String getTimeAsString() {
		return new Date().toString();
	}

	public long getTimeAsLong() {
		return new Date().getTime();
	}

} 

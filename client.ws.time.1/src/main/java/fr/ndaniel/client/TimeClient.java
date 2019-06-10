package fr.ndaniel.client;

import java.net.MalformedURLException;

public class TimeClient {
	
	public static void main(String[] args) throws MalformedURLException {
		
		TimeServerImplService timeServerImplService = new TimeServerImplService();
		TimeServer timeServer = timeServerImplService.getPort(TimeServer.class);
		
		System.out.println("Time as string : " + timeServer.getTimeAsString());
		System.out.println("Time as long : " + timeServer.getTimeAsLong());
	}

}

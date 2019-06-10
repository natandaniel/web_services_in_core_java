package fr.ndaniel.client;

public class TimeClient {
	
	public static void main(String[] args) {
		
		TimeServerImplService timeServerImplService = new TimeServerImplService();
		TimeServer timeServer = timeServerImplService.getTimeServerImplPort();
		
		System.out.println("Time as string : " + timeServer.getTimeAsString());
		System.out.println("Time as long : " + timeServer.getTimeAsLong());
	}

}

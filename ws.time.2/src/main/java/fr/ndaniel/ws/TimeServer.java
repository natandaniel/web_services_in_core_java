package fr.ndaniel.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TimeServer {
	
	@WebMethod
	String getTimeAsString();
	
	@WebMethod
	long getTimeAsLong();

}

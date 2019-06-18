package time;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TimeClient {

	public static void main(String[] args) throws MalformedURLException {

		URL url = new URL("http://localhost:9080/ws/time?wsdl");
		QName qname = new QName("http://time/", "TimeServiceImplService");
		Service service = Service.create(url, qname);

		TimeService timeService = service.getPort(TimeService.class);
		System.out.println(timeService.getTimeAsString());
		System.out.println(timeService.getTimeAsLong());

	}

}

package time;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "server.TimeServer")
public class TimeServiceImpl implements TimeService {

	public String getTimeAsString() {
		return new Date().toString();
	}

	public long getTimeAsLong() {
		return new Date().getTime();
	}

}

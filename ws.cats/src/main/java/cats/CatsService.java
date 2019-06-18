package cats;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CatsService {
	
	@WebMethod
	Cat getCatInfo(String breed) throws Exception;

}

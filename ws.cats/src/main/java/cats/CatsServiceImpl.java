package cats;

import javax.jws.WebService;

@WebService(endpointInterface = "cats.CatsService")
public class CatsServiceImpl implements CatsService {

	private Cat britishShorthair;

	public CatsServiceImpl() {
		britishShorthair = new Cat("Medium", "All colours (common colour - blue", "Short - Medium", 12, 6,
				"Moderate (Weekly)", "Indoor");
	};

	public Cat getCatInfo(String breed) throws Exception {
		
		if("british shorthair".equals(breed)) {
			return britishShorthair;
		}else {
			throw new Exception("Ooops ! cat breed unknown !");
		}
	}

}

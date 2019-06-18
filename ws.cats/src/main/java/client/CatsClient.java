package client;

public class CatsClient {
	
	public static void main(String[] args) throws Exception_Exception {
		CatsServiceImplService service = new CatsServiceImplService();
		CatsService catsService = service.getCatsServiceImplPort();
		System.out.println(catsService.getCatInfo("british shorthair").toString());
		System.out.println(catsService.getCatInfo("abc"));
	}
}

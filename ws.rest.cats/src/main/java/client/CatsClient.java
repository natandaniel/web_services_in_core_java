package client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CatsClient {
	
	private static final String URL = "http://localhost:9082/ws/rest/cats";

	public static void main(String[] args) {
		

		
	}
	
	private void getCats() {
		
		HttpURLConnection connection = getConnection(URL, "GET");
		try {
			connection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static HttpURLConnection getConnection(String urlString, String httpVerb) {
		
		HttpURLConnection connection = null;
		
		URL url;
		try {
			url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(httpVerb);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	private void print(HttpURLConnection connection) {
		
		String xml = "";
		
	}

}

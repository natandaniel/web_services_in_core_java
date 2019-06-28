package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CatsClient {
	
	private static final String URL = "http://localhost:9082/ws/rest/cats";

	public static void main(String[] args) {
		getCats();
	}
	
	private static void getCats() {
		
		HttpURLConnection connection = getConnection(URL, "GET");
		try {
			connection.connect();
			print(connection);
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
	
	private static void print(HttpURLConnection connection) {
		
		BufferedReader bufferedReader = null;
		String xml = "";
		String next = null;
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			while((next = bufferedReader.readLine()) != null) {
				xml += next;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		

		
		System.out.println(xml);
		
	}

}

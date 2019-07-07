package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CatsClient {

	private static final String URL = "http://localhost:9082/ws/rest/cats";

	private static final String POST_CAT_1 = "post_cat_1.xml";

	public static void main(String[] args) {
		getCats();
		getBritishShorthair();
		
		postCat1();
		
		getCats();
		getBritishShorthair();
		getCat1();
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

	private static void getBritishShorthair() {

		HttpURLConnection connection = getConnection(URL + "?name=British%20Shorthair", "GET");
		try {
			connection.connect();
			print(connection);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void getCat1() {

		HttpURLConnection connection = getConnection(URL + "?name=TestCat", "GET");
		try {
			connection.connect();
			print(connection);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void postCat1() {

		HttpURLConnection connection = getConnection(URL, "POST");
		try {
			connection.setRequestProperty("payload", readXmlFileToString(getPathToPostCat1XmlFile()));
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

			while ((next = bufferedReader.readLine()) != null) {
				xml += next;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(xml);

	}

	private static String readXmlFileToString(String path) throws FileNotFoundException, IOException {

		String line = null;
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {

			while ((line = br.readLine()) != null) {
				sb.append(line.trim() + System.getProperty("line.separator"));
			}
		}
		
		String xml = sb.toString();
		xml = xml.replace("\n", "").replace("\r", "");

		//System.out.println("output xml  : " + xml);

		return xml;

	}

	private static String getPathToPostCat1XmlFile() {

		String currentWorkingDir = System.getProperty("user.dir");
		String seperator = System.getProperty("file.separator");
		String path = currentWorkingDir + seperator + "src" + seperator + "main" + seperator + "java" + seperator
				+ "client" + seperator + POST_CAT_1;
		//System.out.println(path);
		return path;
	}

}

[1mdiff --git a/ws.rest.cats/src/main/java/cats/CatsServiceImpl.java b/ws.rest.cats/src/main/java/cats/CatsServiceImpl.java[m
[1mindex 5371078..9d948b0 100644[m
[1m--- a/ws.rest.cats/src/main/java/cats/CatsServiceImpl.java[m
[1m+++ b/ws.rest.cats/src/main/java/cats/CatsServiceImpl.java[m
[36m@@ -53,8 +53,10 @@[m [mpublic class CatsServiceImpl implements Provider<Source> {[m
 		}[m
 [m
 		MessageContext msgCtx = ctx.getMessageContext();[m
[31m-		String httpVerb = (String) MessageContext.HTTP_REQUEST_METHOD;[m
[32m+[m		[32mString httpVerb = (String) msgCtx.get(MessageContext.HTTP_REQUEST_METHOD);[m
 		httpVerb = httpVerb.trim().toUpperCase();[m
[32m+[m[41m		[m
[32m+[m		[32mSystem.out.println("HTTP verb : " + httpVerb);[m[41m [m
 [m
 		if ("GET".equals(httpVerb)) {[m
 			return doGet(msgCtx);[m
[36m@@ -67,8 +69,8 @@[m [mpublic class CatsServiceImpl implements Provider<Source> {[m
 [m
 		String currentWorkingDir = System.getProperty("user.dir");[m
 		String seperator = System.getProperty("file.separator");[m
[31m-		String pathToFile = currentWorkingDir + seperator + "src" + seperator + "main" + seperator + "java" + seperator[m
[31m-				+ "cats" + seperator + fileName;[m
[32m+[m		[32mString pathToFile = currentWorkingDir + seperator + "src" + seperator + "main" + seperator + "resources"[m
[32m+[m				[32m+ seperator + seperator + fileName;[m
 [m
 		int length = (int) new File(pathToFile).length();[m
 		catsByteArray = new byte[length];[m
[1mdiff --git a/ws.rest.cats/src/main/java/client/CatsClient.java b/ws.rest.cats/src/main/java/client/CatsClient.java[m
[1mindex c504043..624db33 100644[m
[1m--- a/ws.rest.cats/src/main/java/client/CatsClient.java[m
[1m+++ b/ws.rest.cats/src/main/java/client/CatsClient.java[m
[36m@@ -1,6 +1,8 @@[m
 package client;[m
 [m
[32m+[m[32mimport java.io.BufferedReader;[m
 import java.io.IOException;[m
[32m+[m[32mimport java.io.InputStreamReader;[m
 import java.net.HttpURLConnection;[m
 import java.net.MalformedURLException;[m
 import java.net.URL;[m
[36m@@ -10,16 +12,15 @@[m [mpublic class CatsClient {[m
 	private static final String URL = "http://localhost:9082/ws/rest/cats";[m
 [m
 	public static void main(String[] args) {[m
[31m-		[m
[31m-[m
[31m-		[m
[32m+[m		[32mgetCats();[m
 	}[m
 	[m
[31m-	private void getCats() {[m
[32m+[m	[32mprivate static void getCats() {[m
 		[m
 		HttpURLConnection connection = getConnection(URL, "GET");[m
 		try {[m
 			connection.connect();[m
[32m+[m			[32mprint(connection);[m
 		} catch (IOException e) {[m
 			e.printStackTrace();[m
 		}[m
[36m@@ -43,9 +44,27 @@[m [mpublic class CatsClient {[m
 		return connection;[m
 	}[m
 	[m
[31m-	private void print(HttpURLConnection connection) {[m
[32m+[m	[32mprivate static void print(HttpURLConnection connection) {[m
 		[m
[32m+[m		[32mBufferedReader bufferedReader = null;[m
 		String xml = "";[m
[32m+[m		[32mString next = null;[m
[32m+[m[41m		[m
[32m+[m		[32mtry {[m
[32m+[m			[32mbufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));[m
[32m+[m[41m			[m
[32m+[m			[32mwhile((next = bufferedReader.readLine()) != null) {[m
[32m+[m				[32mxml += next;[m
[32m+[m			[32m}[m
[32m+[m		[32m} catch (IOException e) {[m
[32m+[m			[32me.printStackTrace();[m
[32m+[m		[32m}[m
[32m+[m[41m		[m
[32m+[m
[32m+[m[41m		[m
[32m+[m
[32m+[m[41m		[m
[32m+[m		[32mSystem.out.println(xml);[m
 		[m
 	}[m
 [m

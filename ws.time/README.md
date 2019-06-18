# Description

A simple illustration of an RPC style SOAP Web Service, provides the current time as a string or long.

Service Endpoint Interface : TimeServer.java

Service Implementation Bean : TimeServerImpl.java

Service publisher : TimeServerPublisher.java

# Minimum requirements to run the example

Core Java 6 (includes Java Api for XML Web Services - JAX-WS 2.x)

or

Core Java 5 + the METRO release of JAX-WS : https://javaee.github.io/metro/download

# Starting the service

From within src/main/java, at the command prompt : 

 - compile the web service code with : javac fr/ndaniel/ws/*.java
 
 - run the web service publisher : java fr.daniel.ws.TimeServerPublisher
 
 - check service contract in browser : http://localhost:9080/time?wsdl
 
 - or check service contract at the command prompt : curl http://localhost:9080/time?wsdl


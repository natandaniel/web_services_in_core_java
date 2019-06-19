# Time

A simple illustration of an RPC style SOAP Web Service, provides the current time as a string or long.

 Service Endpoint Interface : TimeService.java

	Service Implementation Bean : TimeServiceImpl.java

	Service publisher : TimeServicePublisher.java

	Test client : TimeClient.java

# Minimum requirements to run the example

	Core Java 6

# Cmd line instructions to run and test the service

 	cd path/to/project/folder/src/main/java

 	compile the web service server/client code : javac time/*.java
 
	run the web service publisher : java time.TimeServicePublisher
	
	optional : check service contract in browser : http://localhost:9080/ws/time?wsdl
 
 	optional : or check service contract at the command prompt : curl http://localhost:9080/ws/time?wsdl
 
 	run client : java time.TimeClient

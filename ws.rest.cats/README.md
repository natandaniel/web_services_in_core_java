# Cats

A simple illustration of a RESTful Web Service. For now, only GETs and POSTs are handled.

	Service Implementation Bean : CatsServiceImpl.java

	Service publisher : CatsServicePublisher.java
	
	Service resource : cats.xml

	Test client : CatsClient.java 

# Minimum requirements to run the example

	Core Java 8

# Cmd line instructions to run and test the service

 	cd path/to/project/folder/src/main/java

 	compile the web service server code : javac cats/*.java
 
 	run the web service publisher : java cats.CatsServicePublisher
 
  	run GET operation in browser : http://localhost:9082/ws/rest/cats
 
 	run GET operation in browser : http://localhost:9082/ws/rest/cats?name=British%20Shorthair
 
 	compile the web service client code : javac client/*.java



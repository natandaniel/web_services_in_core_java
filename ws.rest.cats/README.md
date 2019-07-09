# Cats

A simple illustration of a RESTful Web Service. 

Allows GETs, POSTs and DELETEs on the cats.xml resource.

One can GET an up to date XML representation of the cats.xml resource.

One can POST a new cat to the service and add a new object to the cats.xml resource.

One can DELETE a cat from the server side resource cats.xml file.

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
	
	run GET operation in browser : http://localhost:9082/ws/rest/cats
	
	run GET operation in browser : http://localhost:9082/ws/rest/cats?name=British%20Shorthair
	
	run GET operation in browser : http://localhost:9082/ws/rest/cats?name=TestCat



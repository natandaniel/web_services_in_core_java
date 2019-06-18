# Cats

A simple illustration of a DOCUMENT style SOAP Web Service, provides a cat's caracteristics by breed.

Expected input, "british shorthair". Other input will cause an error message.

Service Endpoint Interface : CatsService.java

Service Implementation Bean : CatsServiceImpl.java

Service publisher : CatsServicePublisher.java

Test client : CatsClient.java + generated code through wsimport utility 

(at path/to/project/folder/src/main/java : wsimport -p client -keep http://localhost:9081/ws/cats?wsdl)

# Minimum requirements to run the example

Core Java 6 (includes Java Api for XML Web Services - JAX-WS 2.x)

or

Core Java 5 + the METRO release of JAX-WS : https://javaee.github.io/metro/download

# Cmd line instructions to run and test the service

 - cd path/to/project/folder/src/main/java

 - compile the web service code with : javac cats/*.java
 
 - generate the required Java artifacts with : wsgen -cp . cats.CatsServiceImpl
 
 - run the web service publisher : java cats.CatsServicePublisher
 
 - check service contract in browser : http://localhost:9081/ws/cats?wsdl
 
 - or check service contract at the command prompt : curl http://localhost:9081/ws/cats?wsdl
 
 - run client : java cats.CatsClient



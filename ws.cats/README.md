# Cats

A simple illustration of a DOCUMENT style SOAP Web Service, provides a cat's caracteristics by breed.

	Service Endpoint Interface : CatsService.java

	Service Implementation Bean : CatsServiceImpl.java

	Service publisher : CatsServicePublisher.java

	Test client : CatsClient.java + generated code through wsimport utility 

	(at path/to/project/folder/src/main/java : wsimport -p client -keep http://localhost:9081/ws/cats?wsdl)

Note : I have manually added a SOAP handler to monitor the outbound SOAP requests client side. In order to configure the client with my handler, I have added the @HandlerChain annotation in my client service stub generated through wsimport. If you decide to regenerate the client Java artifcats, make sure to add the annotation with the file attribute pointing to the handlers.xml file.

# Minimum requirements to run the example

Core Java 6

# Cmd line instructions to run and test the service

 - cd path/to/project/folder/src/main/java

 - compile the web service code with : javac cats/*.java
 
 - generate the required Java artifacts with : wsgen -cp . cats.CatsServiceImpl
 
 - run the web service publisher : java cats.CatsServicePublisher
 
 - check service contract in browser : http://localhost:9081/ws/cats?wsdl
 
 - or check service contract at the command prompt : curl http://localhost:9081/ws/cats?wsdl
 
 - run client : java cats.CatsClient



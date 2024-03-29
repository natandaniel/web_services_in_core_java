# Web Services in core Java : 

The purpose of the first three examples is to show how to quickly create and run SOAP or RESTful web services with core Java. These examples are kept simple to demonstrate fundamentals. Deployment into servlet containers or application containers is not demonstrated. Servlets from JEE are not used. Core Java 6 is enough to run all the examples, however, in the last two, I have used some functional code with forEach and lambdas for compact code making core Java 8 a requirement.

1. [ws.soap.time](https://github.com/natandaniel/java_projects/tree/master/ws.soap.time)
1. [ws.soap.cats](https://github.com/natandaniel/java_projects/tree/master/ws.soap.cats)
1. [ws.rest.cats](https://github.com/natandaniel/java_projects/tree/master/ws.rest.cats)
  
### [ws.soap.time](https://github.com/natandaniel/java_projects/tree/master/ws.soap.time)

A simple illustration of an **RPC style SOAP** Web Service built with **JAX-WS**. 

Gives the time.

The purpose is to demonstrate how easily a SOAP web service can be built when only simple data types are used.

_Requirements_ : **core Java 6**

### [ws.soap.cats](https://github.com/natandaniel/java_projects/tree/master/ws.soap.cats)

A simple illustration of a **DOCUMENT style SOAP** Web Service built with **JAX-WS**.

Describes the British Shorthair cat.

The purpose is to demonstrate how to build a SOAP web service when custom data types are used. When using one's on Java classes as data types, one must implement a DOCUMENT style service and make use of the **wsgen** utility to generate the Java artifacts necessary to run the service. These artifcats are what enable the binding/unbinding of a custom Java datatype class to an XML document.

The **wsimport** tool, also used in this project, comes in handy to generate client side code.

I also added in the mix some **SOAP API** code with a SOAP handler that can monitor SOAP requests/responses. SOAP handlers are useful not only for monitoring but also for securing the acces to a service through credentials checking etc.

_Requirements_ : **core Java 8**

### [ws.rest.cats](https://github.com/natandaniel/java_projects/tree/master/ws.rest.cats)

A simple illustration of a **RESTful Web Service** built with **JAX-WS**. 

Allows GETs, POSTs, PUTs and DELETEs on the cats.xml resource.

One can GET an up to date XML representation of the cats.xml resource.

One can POST a new cat to the service and add a new object to the cats.xml resource.

One can PUT an existing cat and unpdate the cats.xml resource.

One can DELETE a cat from the server side resource cats.xml file.

The purpose is to demonstrate how to build a RESTful (**REpresentational State Transfer**) web service with core Java. Clients and Servers communicate through the **HTTP (Hypertext Transfer Protocol)** protocol and request/respone messages are contained within the HTTP transport message (in the headers or in the body). No specific format is imposed to the messages as opposed to SOAP web services where messages are SOAP envelopes (XML documents). In this project, POSTs send information within the header for simplicity, but common practice is to use the body of the HTTP transport message.

In a RESTFul web service, one can execute the **CRUD operations (CREATE, READ, UPDATE, DELETE)** with the appropriate **HTTP verbs (POST, GET, PUT, DELETE)**. These operations must be targeted at **a resource** server side (any informational item) that is uniquely identified with a **URI (Uniform Resource Identifier)**. The service has to return a relevant representation of the resource (a **MIME typed** representation, could be text/xml, text/plain etc) that captures the current state of the resource.

_Requirements_ : **core Java 8**


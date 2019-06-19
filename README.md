# This repository holds some of my JAVA/JEE projects. 

# ws.time 
A simple illustration of an RPC style SOAP Web Service. Gives the time. The purpose is to demonstrate how easily a SOAP web service can be built when only simple data types are used (String, int, ...). I have proceeded with the Code First approach to ease developement of the service.

# ws.cats 
A simple illustration of a DOCUMENT style SOAP Web Service. Describes the British Shorthair cat. The purpose is to demonstrate how to build such a web service with custom data types using the wsgen utility and how to build client code easing the development of a client for the service with wsimport. I also added in the mix some SOAP API code with a SOAP handler that can monitor SOAP requests/responses. SOAP handlers are useful not only for monitoring but also for securing the acces to a service through credentials checking etc. I have proceeded with the Code First approach to ease developement of the service. 


package client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CatsService", targetNamespace = "http://cats/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CatsService {


    /**
     * 
     * @param arg0
     * @return
     *     returns client.Cat
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCatInfo", targetNamespace = "http://cats/", className = "client.GetCatInfo")
    @ResponseWrapper(localName = "getCatInfoResponse", targetNamespace = "http://cats/", className = "client.GetCatInfoResponse")
    @Action(input = "http://cats/CatsService/getCatInfoRequest", output = "http://cats/CatsService/getCatInfoResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://cats/CatsService/getCatInfo/Fault/Exception")
    })
    public Cat getCatInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws Exception_Exception
    ;

}

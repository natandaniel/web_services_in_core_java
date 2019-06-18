
package client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCatInfo_QNAME = new QName("http://cats/", "getCatInfo");
    private final static QName _Exception_QNAME = new QName("http://cats/", "Exception");
    private final static QName _GetCatInfoResponse_QNAME = new QName("http://cats/", "getCatInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link GetCatInfoResponse }
     * 
     */
    public GetCatInfoResponse createGetCatInfoResponse() {
        return new GetCatInfoResponse();
    }

    /**
     * Create an instance of {@link GetCatInfo }
     * 
     */
    public GetCatInfo createGetCatInfo() {
        return new GetCatInfo();
    }

    /**
     * Create an instance of {@link Cat }
     * 
     */
    public Cat createCat() {
        return new Cat();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCatInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cats/", name = "getCatInfo")
    public JAXBElement<GetCatInfo> createGetCatInfo(GetCatInfo value) {
        return new JAXBElement<GetCatInfo>(_GetCatInfo_QNAME, GetCatInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cats/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCatInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cats/", name = "getCatInfoResponse")
    public JAXBElement<GetCatInfoResponse> createGetCatInfoResponse(GetCatInfoResponse value) {
        return new JAXBElement<GetCatInfoResponse>(_GetCatInfoResponse_QNAME, GetCatInfoResponse.class, null, value);
    }

}

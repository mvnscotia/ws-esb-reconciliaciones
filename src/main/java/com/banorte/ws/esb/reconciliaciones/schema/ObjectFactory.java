//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.04.29 at 01:07:32 AM CDT 
//


package com.banorte.ws.esb.reconciliaciones.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.banorte.ws.esb.reconciliaciones.schema package. 
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

    private final static QName _UserResponse_QNAME = new QName("http://www.example.org/User", "userResponse");
    private final static QName _UserFault_QNAME = new QName("http://www.example.org/User", "userFault");
    private final static QName _UserRequest_QNAME = new QName("http://www.example.org/User", "userRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.banorte.ws.esb.reconciliaciones.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserRequest }
     * 
     */
    public UserRequest createUserRequest() {
        return new UserRequest();
    }

    /**
     * Create an instance of {@link UserResponse }
     * 
     */
    public UserResponse createUserResponse() {
        return new UserResponse();
    }

    /**
     * Create an instance of {@link UserFault }
     * 
     */
    public UserFault createUserFault() {
        return new UserFault();
    }

    /**
     * Create an instance of {@link UserDetails }
     * 
     */
    public UserDetails createUserDetails() {
        return new UserDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/User", name = "userResponse")
    public JAXBElement<UserResponse> createUserResponse(UserResponse value) {
        return new JAXBElement<UserResponse>(_UserResponse_QNAME, UserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/User", name = "userFault")
    public JAXBElement<UserFault> createUserFault(UserFault value) {
        return new JAXBElement<UserFault>(_UserFault_QNAME, UserFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/User", name = "userRequest")
    public JAXBElement<UserRequest> createUserRequest(UserRequest value) {
        return new JAXBElement<UserRequest>(_UserRequest_QNAME, UserRequest.class, null, value);
    }

}

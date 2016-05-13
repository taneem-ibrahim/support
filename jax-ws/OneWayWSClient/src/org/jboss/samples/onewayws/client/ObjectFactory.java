
package org.jboss.samples.onewayws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.jboss.samples.onewayws.client package. 
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

    private final static QName _Ping_QNAME = new QName("http://localhost.jboss.sample/ws/ping", "ping");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jboss.samples.onewayws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Ping_Type }
     * 
     */
    public Ping_Type createPing_Type() {
        return new Ping_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost.jboss.sample/ws/ping", name = "ping")
    public JAXBElement<Ping_Type> createPing(Ping_Type value) {
        return new JAXBElement<Ping_Type>(_Ping_QNAME, Ping_Type.class, null, value);
    }

}

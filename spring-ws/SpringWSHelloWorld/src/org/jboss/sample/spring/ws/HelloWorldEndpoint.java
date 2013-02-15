/**
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright to this software to the public domain worldwide, pursuant to the CC0 Public Domain Dedication. This software is distributed without any warranty.  See <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package org.jboss.sample.spring.ws;

/**
 * @author taneem
 * Note: generated WSDL at http://localhost:8080/SpringWSHelloWorld/helloworld/helloworld.wsdl
 * Note: soap:address endpoint: http://localhost:8080/SpringWSHelloWorld/hello-world
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

@Endpoint
public class HelloWorldEndpoint {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
	
	private static final String NAMESPACE_URI = "http://localhost.jboss.sample/ws/helloworld";
    private static final String REQUEST_LOCAL_NAME = "helloworldRequest";
    private static final String RESPONSE_LOCAL_NAME = "helloworldResponse";

	@PayloadRoot(localPart = REQUEST_LOCAL_NAME, namespace = NAMESPACE_URI)
	@ResponsePayload
	public Element handleRequest(@RequestPayload Element requestElement) throws Exception {
		String text = requestElement.getChildNodes().item(0).getNodeValue();
		log.info("Request Pay Load: " + text);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element responseElement = document.createElementNS(NAMESPACE_URI, RESPONSE_LOCAL_NAME);
		Text responseText = document.createTextNode("Hello "+ text);
		responseElement.appendChild(responseText);
		return responseElement;
	}
}

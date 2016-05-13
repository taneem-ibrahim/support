package org.jboss.samples.webservices;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://localhost.jboss.sample/ws/ping")
public class Ping {

	@WebMethod()
	@Oneway
	public void ping() {
	    System.out.println("Pinged!");
	}
}

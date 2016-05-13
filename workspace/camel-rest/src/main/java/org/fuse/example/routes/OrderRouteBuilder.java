package org.fuse.example.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OrderRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("cxfrs:bean:orderEndpoint")
		.log("Payload: ${body}")
		.setHeader("orderId", xpath("/orders/order/@id"))
		.log(LoggingLevel.INFO,"Header: ${header.orderId}")
		.split(xpath("/orders/order/company/text()"))
		.log("Split line: ${body}");		
	}

}

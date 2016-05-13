/**
 * 
 */
package com.taneem.camel.file.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author taneem
 *
 */
public class FileRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:in?noop=true").id("testContext")
		.filter().xpath("/order[not (@test)]")
		.log("File content:${body}").end();	
	}
}

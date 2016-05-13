/**
 * 
 */
package org.fuse.example.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author taneem
 *
 */
@Path("/orders/")
public class OrderService {
	
	@POST
    @Path("/submitOrders/")
    @Produces(MediaType.APPLICATION_JSON)
    public String submitOrders(String input){
        return null;
    }

}

/*
 * test notes:
 * localhost:8182/orderservice/orders/submitOrders
 * <orders>
 *  <order>
   <company>Yonex</company>
   <item>racket</item>
   <quantity>120</quantity>
   </order>
   <order>
   <company>Victor</company>
   <item>racket</item>
   <quantity>60</quantity>
   </order>
<orders>
 */
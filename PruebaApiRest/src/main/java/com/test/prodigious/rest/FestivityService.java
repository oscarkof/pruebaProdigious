/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.prodigious.rest;

import com.test.prodigious.rest.model.Festivity;
import java.util.Calendar;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author osmar
 */
@Path("/festivity")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class FestivityService {
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/person/id/{id}/")
    public Response getPersonJSON(@PathParam("id")String personId) throws Exception{
        Festivity response = new Festivity();        
        response.setName("OSCAR");
        response.setPlace("ZIPA");
        response.setStart(Calendar.getInstance().getTime());
        response.setEnd(Calendar.getInstance().getTime());
        return Response.ok(response).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public String createCustomer(Festivity object) {
        System.out.println("object: "+object);
        return "OK CREATE";
    }
    
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public String updateCustomer(Festivity object) {
        System.out.println("object: "+object);
        return "OK UPDATE";
    }
}

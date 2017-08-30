/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.prodigious.rest;

import com.test.prodigious.rest.model.Festivity;
import com.test.prodigious.util.Database;
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
    public Response createCustomer(Festivity object) {
        String returnMessage = "Create Successfully";
        int statusCode = 200;
        if(!Database.save(object)){
            returnMessage = "Error happen in a layer below the REST API layer";
            statusCode = 500;
        }        
        
        return Response.status(statusCode).entity(returnMessage).build();
    }
    
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCustomer(Festivity object) {
        String returnMessage = "Update Successfully";
        int statusCode = 200;
        if(!Database.update(object)){
            returnMessage = "Error happen in a layer below the REST API layer";
            statusCode = 500;
        }        
        
        return Response.status(statusCode).entity(returnMessage).build();
    }
}

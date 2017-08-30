package com.test.prodigious.rest;

import com.test.prodigious.rest.model.Festivities;
import com.test.prodigious.rest.model.Festivity;
import com.test.prodigious.rest.model.ResponseCustom;
import com.test.prodigious.util.Database;
import com.test.prodigious.util.ValidateData;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class representative of the REST service, contains the information of the 
 * HTTP methods (GET, POST and PUT) for the different operations 
 * requested in the Java technical test
 * @author Oscar Martinez
 */
@Path("/festivity")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class FestivityService {
    
    /**
     * Method to consult the festivities by means of the wildcards Festivities 
     * by: name, start date, date range, name of the place where they are Celebrated
     * @param name name of the Festivity
     * @param start date start of the Festivity
     * @param min min range to query for start date of the Festivity
     * @param max max range to query for start date of the Festivity
     * @param place place where they are Celebrated
     * @param request request object to get the language accept to i18n
     * @return XML or JSON represntation of the Festivities, it depends of 
     * Header Accept - {application/json, application/xml}
     * @throws Exception 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/query")
    public Response getFestivities( @QueryParam("name") @DefaultValue("default") String name,
                                    @QueryParam("start") @DefaultValue("default") String start,
                                    @QueryParam("min") @DefaultValue("default") String min,
                                    @QueryParam("max") @DefaultValue("default") String max,
                                    @QueryParam("place") @DefaultValue("default") String place,
                                    @Context HttpServletRequest request) throws Exception{
        Response responseReturn = null;
                
        Locale requestLocale = request.getLocale();
        ResourceBundle msg = ResourceBundle.getBundle("messages", requestLocale);
        List<Festivity> festivityList = Database.getFestivities(name, start, min, max, place);
        if (festivityList == null || festivityList.isEmpty()) {
                ResponseCustom response = new ResponseCustom();
                response.setStatusCode(404);
                response.setStatusMessage(msg.getString("datanotfound"));
                responseReturn = Response.status(response.getStatusCode()).entity(response).build();
            }else{
                Festivities festivities = new Festivities();
                festivities.setFestivities(festivityList);
                responseReturn = Response.ok(festivities).build();
            }
        return responseReturn;
    }
    
    /**
     * Method to consult all festivities stored in dataBase
     * @param request request object to get the language accept to i18n
     * @return XML or JSON represntation of the Festivities with the status code 200, it depends of 
     * Header Accept - {application/json, application/xml}, in case of the search
     * that has not results it return status code 404 with the localized Message
     * @throws Exception 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllFestivities(@Context HttpServletRequest request) throws Exception{
        Response responseReturn = null;
        try {
            Locale requestLocale = request.getLocale();
            ResourceBundle msg = ResourceBundle.getBundle("messages", requestLocale);
            List<Festivity> festivityList = Database.getAllFestivities();
            if (festivityList == null || festivityList.isEmpty()) {
                ResponseCustom response = new ResponseCustom();
                response.setStatusCode(404);
                response.setStatusMessage(msg.getString("datanotfound"));
                responseReturn = Response.status(response.getStatusCode()).entity(response).build();
            }else{
                Festivities festivities = new Festivities();
                festivities.setFestivities(festivityList);
                responseReturn = Response.ok(festivities).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseReturn;
    }
    
    /**
     * Method to store an specific festivity in dataBase, the consume entity 
     * can be XML or JSON it depends of Header Content-Type - {application/json, application/xml}
     * @param object representational of the Festivity Entity
     * @param request request object to get the language accept to i18n
     * @return Text plan with associated status code:
     *   - 200 Ok, create sucessfully
     *   - 400 Error, Missing Data
     *   - 400 Error, Dates start, end are not consistent
     *   - 500 Error, other error in layer below REST API layer
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response createFestivity(Festivity object, @Context HttpServletRequest request) {
        Locale requestLocale = request.getLocale();
        ResourceBundle msg = ResourceBundle.getBundle("messages", requestLocale);
        ResponseCustom response = new ResponseCustom();
        response.setStatusMessage(msg.getString("createok"));
        response.setStatusCode(200);
        if(!ValidateData.validateFestivity(object)){
            response.setStatusMessage(msg.getString("missingdata"));
            response.setStatusCode(400);
        }else if(!ValidateData.validateFestivityDates(object)){
            response.setStatusMessage(msg.getString("inconsistentdates"));
            response.setStatusCode(400);
        }else if(!Database.save(object)){
            response.setStatusMessage(msg.getString("servererrorinsert"));
            response.setStatusCode(500);
        }        
        
        return Response.status(response.getStatusCode()).entity(response.getStatusMessage()).build();
    }
    
    /**
     * Method to update an existent festivity in dataBase, the consume entity 
     * can be XML or JSON it depends of Header Content-Type - {application/json, application/xml}
     * @param object representational of the Festivity Entity
     * @param request request object to get the language accept to i18n
     * @return Text plan with associated status code:
     *   - 200 Ok, create sucessfully
     *   - 400 Error, Missing Data
     *   - 400 Error, Dates start, end are not consistent
     *   - 500 Error, other error in layer below REST API layer
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response udpateFestivity(Festivity object, @Context HttpServletRequest request) {
        Locale requestLocale = request.getLocale();
        ResourceBundle msg = ResourceBundle.getBundle("messages", requestLocale);
        ResponseCustom response = new ResponseCustom();
        response.setStatusMessage(msg.getString("updateok"));
        response.setStatusCode(200);
        if(!ValidateData.validateFestivity(object)){
            response.setStatusMessage(msg.getString("missingdata"));
            response.setStatusCode(400);
        }else if(!ValidateData.validateFestivityDates(object)){
            response.setStatusMessage(msg.getString("inconsistentdates"));
            response.setStatusCode(400);
        }else if(!Database.update(object)){
            response.setStatusMessage(msg.getString("servererrorupdate"));
            response.setStatusCode(500);
        }        
        
        return Response.status(response.getStatusCode()).entity(response.getStatusMessage()).build();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.fink.evidencija_robe.data.Prostor;
import rs.ac.fink.evidencija_robe.service.ProstorService;
import rs.ac.fink.evidencija_robe.exception.RobaException;


@Path("prostor")
public class ProstorRest {
    private final ProstorService prostorService = ProstorService.getInstance();
    
    @GET
    @Path("/{prostorID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prostor getProductById(@PathParam("prostorID") int prostorID) throws RobaException {
        return prostorService.findProstorById(prostorID);
    }
    
    @GET
    @Path("/naziv/{prostorName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prostor getProductById(@PathParam("prostorName") String prostorName) throws RobaException {
        return prostorService.findProstorByName(prostorName);
    }
}


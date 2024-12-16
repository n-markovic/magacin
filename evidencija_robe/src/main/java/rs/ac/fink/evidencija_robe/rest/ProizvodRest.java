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
import rs.ac.fink.evidencija_robe.data.Proizvod;
import rs.ac.fink.evidencija_robe.service.ProizvodService;
import rs.ac.fink.evidencija_robe.exception.RobaException;

@Path("proizvod")
public class ProizvodRest {
    private final ProizvodService prozivodService = ProizvodService.getInstance();
    
    @GET
    @Path("/naziv/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proizvod getProductByName(@PathParam("productName") String productName) throws RobaException {
        return prozivodService.findProizvodByName(productName);
    }
    
    @GET
    @Path("/id/{productID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proizvod getProductByName(@PathParam("productID") int productID) throws RobaException {
        return prozivodService.findProizvodById(productID);
    }
   
   @GET
    @Path("/tip/{productType}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proizvod> getProductsByType(@PathParam("productType") String productType) throws RobaException {
        return prozivodService.findProizvodiByType(productType);
    }

}



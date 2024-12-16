/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
    private final ProizvodService proizvodService = ProizvodService.getInstance();
    
    @GET
    //@Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proizvod> getProductAll() throws RobaException {
        return proizvodService.findProizvodiAll();
    }
    
    @GET
    @Path("/naziv/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proizvod getProductByName(@PathParam("productName") String productName) throws RobaException {
        return proizvodService.findProizvodByName(productName);
    }
    
    @GET
    @Path("/id/{productID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proizvod getProductByName(@PathParam("productID") int productID) throws RobaException {
        return proizvodService.findProizvodById(productID);
    }
   
    @GET
    @Path("/tip/{productType}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proizvod> getProductsByType(@PathParam("productType") String productType) throws RobaException {
        return proizvodService.findProizvodiByType(productType);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Proizvod proizvod) throws RobaException{
            proizvodService.addNewProizvod(proizvod);
            return Response.ok().build();
    }
    
    @PUT
    @Path("/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("productName") String productName) throws RobaException {
            proizvodService.updateProizvod(productName);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productName") String productName) throws RobaException {
            proizvodService.deleteProizvod(productName);
            return Response.ok().build();
    }

}



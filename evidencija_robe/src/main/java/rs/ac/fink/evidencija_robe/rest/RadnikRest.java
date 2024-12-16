/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.ac.fink.evidencija_robe.data.Kredencijali;
import rs.ac.fink.evidencija_robe.data.Radnik;
import rs.ac.fink.evidencija_robe.service.RadnikService;
import rs.ac.fink.evidencija_robe.exception.RobaException;

@Path("radnik")
public class RadnikRest {
    private final RadnikService radnikService = RadnikService.getInstance();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRadnik(Radnik radnik) throws RobaException{
            radnikService.addNewRadnik(radnik);
            return Response.ok().build();
    }
    
    @GET
    @Path("/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Radnik getProductByName(@PathParam("productName") int productName) throws RobaException {
        return radnikService.findRadnik(productName);
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUserLogin(Kredencijali kredencijali) throws RobaException {
    boolean isAuthenticated = radnikService.loginUser(kredencijali.getUsername(), kredencijali.getPassword());
    if (isAuthenticated) {
        return Response.ok("Dobrodosli, " + kredencijali.getUsername() + "!").build();
    } else {
        return Response.status(Response.Status.UNAUTHORIZED).entity("Neispravno korisnicko ime ili lozinka.").build();
    }
    }

}



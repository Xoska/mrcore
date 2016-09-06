package pfe.com.mrcore.clientapi.service;

import pfe.com.mrcore.clientapi.dto.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SessionAPIService {

    @POST
    @Path("/login")
    Session login(@QueryParam("username") String username,
                  @QueryParam("password") String password);

    @POST
    @Path("/logout")
    void logout(Session session);
}

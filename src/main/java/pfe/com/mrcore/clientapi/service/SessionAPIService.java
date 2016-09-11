package pfe.com.mrcore.clientapi.service;

import pfe.com.mrcore.clientapi.dto.session.Credential;
import pfe.com.mrcore.clientapi.dto.session.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SessionAPIService {

    @POST
    @Path("/login")
    Session login(Credential credential);

    @POST
    @Path("/logout")
    void logout(@QueryParam("id_session") String idSession);
}

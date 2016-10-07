package pfe.com.mrcore.clientapi.service;

import pfe.com.mrcore.clientapi.dto.session.Credential;
import pfe.com.mrcore.clientapi.dto.session.Session;
import pfe.com.mrcore.core.utils.RequiresAuthentication;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SessionAPIService {

    @POST
    @Path("/login")
    Session login(Credential credential);

    @GET
    @Path("/logout")
    @RequiresAuthentication
    @RolesAllowed({"MEMBER", "PRIVILEGED_MEMBER", "ADMINISTRATOR"})
    Response logout(@QueryParam("id_profile") Integer idProfile);
}

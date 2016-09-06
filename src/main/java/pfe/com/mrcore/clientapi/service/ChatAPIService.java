package pfe.com.mrcore.clientapi.service;

import pfe.com.mrcore.clientapi.dto.Profile;
import pfe.com.mrcore.clientapi.dto.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ChatAPIService {

    @GET
    @Path("/{id_profile}")
    Profile getProfile(@PathParam("id_profile") Integer profileId,
                       Session session);

    @POST
    Profile create(Profile profile);

    @PUT
    @Path("/{id_profile}")
    Profile update(@PathParam("id_profile") Integer profileId,
                   Profile profile,
                   Session session);

    @DELETE
    @Path("/{id_profile}")
    Response delete(@PathParam("id_profile") Integer profileId,
                    Session session);
}

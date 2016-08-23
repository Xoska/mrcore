package pfe.com.mrcore.clientapi.service;

import pfe.com.mrcore.clientapi.dto.Profile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProfileAPIService {

    @GET
    @Path("/{id_profile}")
    Profile getProfile(@PathParam("id_profile") Integer profileId);

    @POST
    Profile create(Profile profile);

    @PUT
    @Path("/{id_profile}")
    Profile update(@PathParam("id_profile") Integer profileId,
                   Profile profile);

    @DELETE
    @Path("/{id_profile}")
    Response delete(@PathParam("id_profile") Integer profileId);
}

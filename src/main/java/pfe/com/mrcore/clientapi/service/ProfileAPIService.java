package pfe.com.mrcore.clientapi.service;

import pfe.com.mrcore.clientapi.dto.profile.Goal;
import pfe.com.mrcore.clientapi.dto.profile.Profile;
import pfe.com.mrcore.clientapi.dto.profile.Sex;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProfileAPIService {

    @GET
    @Path("/{id_profile}")
    Profile getProfile(@PathParam("id_profile") Integer idProfile,
                       @QueryParam("id_session") String idSession);

    @POST
    Profile create(Profile profile);

    @PUT
    @Path("/{id_profile}")
    Profile update(@PathParam("id_profile") Integer idProfile,
                   @QueryParam("id_session") String idSession,
                   Profile profile);

    @DELETE
    @Path("/{id_profile}")
    Response delete(@PathParam("id_profile") Integer idProfile,
                    @QueryParam("id_session") String idSession);

    @GET
    @Path("/goals")
    List<Goal> getGoals();

    @GET
    @Path("/sexes")
    List<Sex> getSexes();

}

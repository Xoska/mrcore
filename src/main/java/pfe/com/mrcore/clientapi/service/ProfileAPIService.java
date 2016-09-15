package pfe.com.mrcore.clientapi.service;

import pfe.com.mrcore.clientapi.dto.profile.Goal;
import pfe.com.mrcore.clientapi.dto.profile.Profile;
import pfe.com.mrcore.clientapi.dto.profile.Sex;
import pfe.com.mrcore.core.utils.RequiresAuthentication;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProfileAPIService {

    @GET
    @Path("/{id_profile}")
    @RequiresAuthentication
    @RolesAllowed({"MEMBER", "PRIVILEGED_MEMBER", "ADMINISTRATOR"})
    Profile getProfile(@PathParam("id_profile") Integer idProfile);

    @POST
    Profile create(Profile profile);

    @PUT
    @Path("/{id_profile}")
    @RequiresAuthentication
    @RolesAllowed({"MEMBER", "PRIVILEGED_MEMBER", "ADMINISTRATOR"})
    Profile update(@PathParam("id_profile") Integer idProfile,
                   Profile profile);

    @DELETE
    @Path("/{id_profile}")
    @RequiresAuthentication
    @RolesAllowed({"MEMBER", "PRIVILEGED_MEMBER", "ADMINISTRATOR"})
    Response delete(@Context SecurityContext session,
                    @PathParam("id_profile") Integer idProfile);

    @GET
    @Path("/goals")
    List<Goal> getGoals();

    @GET
    @Path("/sexes")
    List<Sex> getSexes();

}

package pfe.com.mrcore.clientapi.service;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;
import pfe.com.mrcore.clientapi.dto.chat.Post;
import pfe.com.mrcore.clientapi.dto.chat.Room;
import pfe.com.mrcore.clientapi.dto.chat.Search;
import pfe.com.mrcore.core.utils.RequiresAuthentication;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ChatAPIService {

    @POST
    @Path("/search")
    @RequiresAuthentication
    @RolesAllowed({"MEMBER", "PRIVILEGED_MEMBER", "ADMINISTRATOR"})
    Room search(@QueryParam("id_profile") Integer idProfile,
                Search search);

    @GET
    @Path("/join/{id_room}")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    @RequiresAuthentication
    @RolesAllowed({"MEMBER", "PRIVILEGED_MEMBER", "ADMINISTRATOR"})
    EventOutput joinRoom(@PathParam("id_room") String roomId,
                         @QueryParam("id_profile") Integer idProfile);

    @GET
    @Path("/leave/{id_room}")
    @RequiresAuthentication
    @RolesAllowed({"MEMBER", "PRIVILEGED_MEMBER", "ADMINISTRATOR"})
    Response leaveRoom(@PathParam("id_room") String idRoom);

    @POST
    @Path("/post/{id_room}")
    void post(@PathParam("id_room") String idRoom,
              Post post);
}

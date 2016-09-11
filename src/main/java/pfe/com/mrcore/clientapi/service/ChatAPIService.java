package pfe.com.mrcore.clientapi.service;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;
import pfe.com.mrcore.clientapi.dto.chat.Post;
import pfe.com.mrcore.clientapi.dto.chat.Search;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ChatAPIService {

    @POST
    @Path("/search")
    String search(@QueryParam("id_profile") Integer idProfile,
                  @QueryParam("id_session") String idSession,
                  Search search);

    @GET
    @Path("/join/{id_room}")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    EventOutput joinRoom(@PathParam("id_room") String roomId,
                         @QueryParam("id_profile") Integer idProfile,
                         @QueryParam("id_session") String idSession);

    @DELETE
    @Path("/leave/{id_room}")
    String leaveRoom(@PathParam("id_room") String idRoom);


    @POST
    @Path("/post/{id_room}")
    void post(@PathParam("id_room") String idRoom,
              @QueryParam("id_profile") Integer idProfile,
              @QueryParam("id_session") String idSession,
              Post post);


}

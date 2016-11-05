package pfe.com.mrcore.core.service;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import pfe.com.mrcore.clientapi.dto.chat.Post;
import pfe.com.mrcore.clientapi.dto.chat.Room;
import pfe.com.mrcore.clientapi.dto.chat.Search;
import pfe.com.mrcore.clientapi.service.ChatAPIService;
import pfe.com.mrcore.core.model.chat.QueueEntity;
import pfe.com.mrcore.core.model.profile.ProfileEntity;
import pfe.com.mrcore.core.repository.chat.QueueRepository;
import pfe.com.mrcore.core.repository.profile.ProfileRepository;
import pfe.com.mrcore.core.utils.DateCalculator;
import pfe.com.mrcore.core.utils.CustomWebExceptionHandler;
import pfe.com.mrcore.core.utils.IdentifierGenerator;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService implements ChatAPIService {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private DateCalculator dateCalculator;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    private static final Map<String, SseBroadcaster> ROOM_SSE_BROADCASTER = new ConcurrentHashMap<>();

    private final static Logger logger = Logger.getLogger(ChatService.class);

    @Override
    @Transactional
    public Room search(Integer idProfile, Search search) {

        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");
        Validate.notNull(search, "Missing mandatory parameter [search]");

        removeFromQueue(idProfile);

        try {

            ProfileEntity profileEntity = profileRepository.findOne(idProfile);

            List<QueueEntity> queueEntities = queueRepository.searchMatch(search.getIdsCity(), profileEntity.getIdCity(),
                    search.getIdCountry(), profileEntity.getIdCountry(), search.getIdState(), profileEntity.getIdState(),
                    search.getIdSex(), profileEntity.getIdSex(), search.getIdGoal(), search.getAgeMin(), search.getAgeMax(),
                    dateCalculator.getAge(profileEntity.getBirthdayDate()));

            String idRoom;
            String owner;

            if (queueEntities.isEmpty()) {

                idRoom = identifierGenerator.nextId(32);
                owner = profileEntity.getUsername();

                queueRepository.save(buildQueueEntity(idRoom, profileEntity, search));

                registerRoom(idRoom);
            }
            else {

                QueueEntity queueEntityPriority = queueEntities.get(0);
                idRoom = queueEntityPriority.getIdRoom();
                owner = queueEntityPriority.getUsername();

                queueRepository.delete(queueEntityPriority);
            }

            return buildRoom(idRoom, owner);
        } catch (Exception e) {

            logger.error(String.format("Error while searching for match with profile [%s]", idProfile), e);
        }

        return null;
    }

    @Override
    public @ResponseBody EventOutput joinRoom(String idRoom) {

        try {

            if (ROOM_SSE_BROADCASTER.containsKey(idRoom)) {

                EventOutput eventOutput = new EventOutput();
                ROOM_SSE_BROADCASTER.get(idRoom).add(eventOutput);

                return eventOutput;
            }
        } catch (Exception e) {

            logger.error(String.format("Error while joining room [%s]", idRoom), e);
        }

        return null;
    }

    @Override
    @Transactional
    public Response leaveRoom(String idRoom, Integer idProfile) {

        Validate.notNull(idRoom, "Missing mandatory parameter [idRoom]");
        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");

        try {

            removeFromQueue(idProfile);

            if (ROOM_SSE_BROADCASTER.containsKey(idRoom)) {

                ROOM_SSE_BROADCASTER.remove(idRoom);
            }

            return Response.accepted().build();
        } catch (Exception e) {

            logger.error(String.format("Error while removing room [%s] from SSE map", idRoom), e);
        }

        return Response.serverError().build();
    }

    @Override
    public void post(String idRoom, Post post) {

        Validate.notNull(post, "Missing mandatory parameter [post]");

        try {

            if (ROOM_SSE_BROADCASTER.containsKey(idRoom)) {

                ROOM_SSE_BROADCASTER.get(idRoom).broadcast(buildEvent(post));
            }

        } catch (Exception e) {

            logger.error(String.format("Error while posting with profile [%s]", post.getIdProfile()), e);
        }
    }

    public void removeFromQueue(Integer idProfile) {

        try {

            if (queueRepository.countByIdProfile(idProfile) > 0) {

                queueRepository.deleteByIdProfile(idProfile);
            }
        } catch (Exception e) {

            logger.error(String.format("Error while removing profile [%s] from queue", idProfile), e);
        }
    }

    private void registerRoom(String idRoom) {

        if (!ROOM_SSE_BROADCASTER.containsKey(idRoom)) {

            ROOM_SSE_BROADCASTER.put(idRoom, new SseBroadcaster());
        }
    }

    private QueueEntity buildQueueEntity(String idRoom, ProfileEntity profileEntity, Search search) {

        QueueEntity queueEntity = new QueueEntity();

        queueEntity.setIdProfile(profileEntity.getIdProfile());
        queueEntity.setIdCity(profileEntity.getIdCity());
        queueEntity.setIdCountry(profileEntity.getIdCountry());
        queueEntity.setIdState(profileEntity.getIdState());
        queueEntity.setZipCode(profileEntity.getZipCode());
        queueEntity.setIdSex(profileEntity.getIdSex());
        queueEntity.setUsername(profileEntity.getUsername());
        queueEntity.setAge(dateCalculator.getAge(profileEntity.getBirthdayDate()));

        queueEntity.setIdsCitySearch(search.getIdsCity());
        queueEntity.setIdStateSearch(search.getIdState());
        queueEntity.setIdCountrySearch(search.getIdCountry());
        queueEntity.setIdSexSearch(search.getIdSex());
        queueEntity.setIdGoal(search.getIdGoal());
        queueEntity.setAgeMinSearch(search.getAgeMin());
        queueEntity.setAgeMaxSearch(search.getAgeMax());

        queueEntity.setIdRoom(idRoom);
        queueEntity.setCreationDate(new Date());

        return queueEntity;
    }

    private static OutboundEvent buildEvent(Post post) {

        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();

        return eventBuilder.name("message")
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .data(Post.class, post)
                .build();
    }

    private Room buildRoom(String identifier, String owner) {

        Room room = new Room();

        room.setIdRoom(identifier);
        room.setEnteredDate(new Date());
        room.setOwner(owner);

        return room;
    }
}

package pfe.com.mrcore.core.service;

import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.com.mrcore.clientapi.dto.chat.Post;
import pfe.com.mrcore.clientapi.dto.chat.Search;
import pfe.com.mrcore.clientapi.service.ChatAPIService;
import pfe.com.mrcore.core.model.chat.QueueEntity;
import pfe.com.mrcore.core.model.profile.ProfileEntity;
import pfe.com.mrcore.core.repository.chat.QueueRepository;
import pfe.com.mrcore.core.repository.profile.ProfileRepository;
import pfe.com.mrcore.core.repository.profile.SexRepository;
import pfe.com.mrcore.core.utils.AgeCalculator;
import pfe.com.mrcore.core.utils.CustomWebExceptionHandler;
import pfe.com.mrcore.core.utils.IdentifierGenerator;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService implements ChatAPIService {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AgeCalculator ageCalculator;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Autowired
    private Mapper mapper;

    private static final Integer ROLE_REQUIRED_PROFILE = 3;

    private static final Map<String, SseBroadcaster> ROOM_SSE_BROADCASTER = new ConcurrentHashMap<>();


    @Override
    public String search(Integer idProfile, String idSession, Search search) {

        if (sessionService.isSessionValid(idSession, idProfile, ROLE_REQUIRED_PROFILE)) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "SESSION_INVALID");
        }

        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");

        QueueEntity queueEntity = queueRepository.findByIdProfile(idProfile);

        if (queueEntity == null) {

            String identifier = identifierGenerator.nextId();
            queueEntity = buildQueueEntity(identifier, idProfile, search);

            queueRepository.save(queueEntity);
        }

        registerRoom(queueEntity.getIdRoom());

        return queueEntity.getIdRoom();
    }

    @Override
    public EventOutput joinRoom(String idRoom, Integer idProfile, String idSession) {

        if (sessionService.isSessionValid(idSession, idProfile, ROLE_REQUIRED_PROFILE)) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "SESSION_INVALID");
        }

        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");

        EventOutput eventOutput = new EventOutput();
        ROOM_SSE_BROADCASTER.get(idRoom).add(eventOutput);

        return eventOutput;
    }

    @Override
    public String leaveRoom(String idRoom) {

        return null;
    }

    @Override
    public void post(String idRoom, Integer idProfile, String idSession, Post post) {

        if (sessionService.isSessionValid(idSession, idProfile, ROLE_REQUIRED_PROFILE)) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "SESSION_INVALID");
        }

        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");

        ROOM_SSE_BROADCASTER.get(idRoom).broadcast(buildEvent(post));
    }

    private void registerRoom(String idRoom) {

        ROOM_SSE_BROADCASTER.put(idRoom, new SseBroadcaster());
    }

    private QueueEntity buildQueueEntity(String idRoom, Integer idProfile, Search search) {

        ProfileEntity profileEntity = profileRepository.findOne(idProfile);

        QueueEntity queueEntity = new QueueEntity();

        queueEntity.setIdProfile(idProfile);
        queueEntity.setIdCity(profileEntity.getIdCity());
        queueEntity.setIdCountry(profileEntity.getIdCountry());
        queueEntity.setIdState(profileEntity.getIdState());
        queueEntity.setZipCode(profileEntity.getZipCode());
        queueEntity.setIdSex(profileEntity.getIdSex());
        queueEntity.setIdSexSearch(search.getIdSex());
        queueEntity.setIdGoal(search.getIdGoal());
        queueEntity.setIdRoom(idRoom);
        queueEntity.setAge(ageCalculator.getAge(profileEntity.getBirthdayDate()));
        queueEntity.setAgeMinSearch(search.getAgeMin());
        queueEntity.setAgeMaxSearch(search.getAgeMax());
        queueEntity.setCreationDate(new Date());

        return queueEntity;
    }

    private static OutboundEvent buildEvent(Post post) {

        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();

        return eventBuilder.name("post")
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .data(Post.class, post)
                .build();
    }
}

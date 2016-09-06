package pfe.com.mrcore.core.service;

import pfe.com.mrcore.clientapi.dto.Profile;
import pfe.com.mrcore.clientapi.dto.Session;
import pfe.com.mrcore.clientapi.service.SessionAPIService;
import pfe.com.mrcore.core.model.ProfileEntity;
import pfe.com.mrcore.core.model.SessionEntity;
import pfe.com.mrcore.core.repository.ProfileRepository;
import pfe.com.mrcore.core.repository.SessionRepository;
import pfe.com.mrcore.core.utils.CustomWebExceptionHandler;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfe.com.mrcore.core.utils.SessionIdentifierGenerator;

import javax.ws.rs.core.Response;
import java.util.Date;

@Service
public class SessionService implements SessionAPIService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionIdentifierGenerator sessionIdentifierGenerator;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public Session login(String username, String password) {

        Validate.notNull(username, "Empty query param [username]");
        Validate.notNull(password, "Empty query param [password]");

        ProfileEntity profileEntity = profileRepository.findByUsernameAndPassword(username, password);

        if (profileEntity == null) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "INVALID_CREDENTIALS");
        }

        SessionEntity sessionEntity = sessionRepository.findByIdProfile(profileEntity.getIdProfile());

        if (sessionEntity != null) {

            return mapper.map(sessionEntity, Session.class);
        }

        return createSession(profileEntity);
    }

    @Override
    @Transactional
    public void logout(Session session) {

        Validate.notNull(session, "Missing mandatory parameter [session]");

        sessionRepository.delete(session.getIdSession());
    }

    @Transactional
    public Boolean isSessionValid(Session session, Integer IdProfile,  Integer IdRole) {

        SessionEntity sessionEntity = sessionRepository.findOne(session.getIdSession());

        return sessionEntity != null && sessionEntity.getIdProfile().equals(IdProfile) && sessionEntity.getIdRole() <= IdRole;
    }

    private Session createSession(ProfileEntity profileEntity) {

        SessionEntity sessionEntity = new SessionEntity();

        sessionEntity.setIdSession(sessionIdentifierGenerator.nextSessionId());
        sessionEntity.setIdProfile(profileEntity.getIdProfile());
        sessionEntity.setIdRole(profileEntity.getRole().getIdRole());
        sessionEntity.setCreationDate(new Date());
        sessionEntity.setLastActionDate(new Date());

        return mapper.map(sessionRepository.save(sessionEntity), Session.class);
    }
}

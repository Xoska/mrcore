package pfe.com.mrcore.core.service;

import pfe.com.mrcore.clientapi.dto.session.Credential;
import pfe.com.mrcore.clientapi.dto.session.Session;
import pfe.com.mrcore.clientapi.service.SessionAPIService;
import pfe.com.mrcore.core.model.profile.ProfileEntity;
import pfe.com.mrcore.core.model.session.SessionEntity;
import pfe.com.mrcore.core.repository.profile.ProfileRepository;
import pfe.com.mrcore.core.repository.session.SessionRepository;
import pfe.com.mrcore.core.utils.CustomWebExceptionHandler;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfe.com.mrcore.core.utils.IdentifierGenerator;

import javax.ws.rs.core.Response;
import java.util.Date;

@Service
public class SessionService implements SessionAPIService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public Session login(Credential credential) {

        Validate.notNull(credential, "Empty query param [credential]");

        ProfileEntity profileEntity = profileRepository.findByUsernameAndPassword(credential.getUsername(), credential.getUsername());

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
    public void logout(String idSession) {

        Validate.notNull(idSession, "Missing mandatory parameter [idSession]");

        sessionRepository.delete(idSession);
    }

    @Transactional
    public Boolean isSessionValid(String idSession, Integer idProfile, Integer idRole) {

        Validate.notNull(idSession, "Missing mandatory parameter [idSession]");

        SessionEntity sessionEntity = sessionRepository.findOne(idSession);

        return sessionEntity != null && sessionEntity.getIdProfile().equals(idProfile) && sessionEntity.getIdRole() <= idRole;
    }

    private Session createSession(ProfileEntity profileEntity) {

        SessionEntity sessionEntity = new SessionEntity();

        sessionEntity.setIdSession(identifierGenerator.nextId());
        sessionEntity.setIdProfile(profileEntity.getIdProfile());
        sessionEntity.setIdRole(profileEntity.getRole().getIdRole());
        sessionEntity.setCreationDate(new Date());
        sessionEntity.setLastActionDate(new Date());

        return mapper.map(sessionRepository.save(sessionEntity), Session.class);
    }
}

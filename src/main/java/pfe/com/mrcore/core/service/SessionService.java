package pfe.com.mrcore.core.service;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static Logger logger = Logger.getLogger(SessionService.class);

    @Override
    @Transactional
    public Session login(Credential credential) {

        Validate.notNull(credential, "Empty query param [credential]");

        ProfileEntity profileEntity = profileRepository.findByUsername(credential.getUsername());

        if (profileEntity != null && passwordEncoder.matches(credential.getPassword(), profileEntity.getPassword())) {

            try {

                SessionEntity sessionEntity = sessionRepository.findByIdProfile(profileEntity.getIdProfile());

                if (sessionEntity != null) {

                    return mapper.map(sessionEntity, Session.class);
                }

                return createSession(profileEntity);
            } catch(Exception e) {

                logger.error(String.format("Error while logging in with username [%s]", credential.getUsername()), e);
            }
        }
        else {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "INVALID_CREDENTIALS");
        }

        return null;
    }

    @Override
    @Transactional
    public Response logout(Integer idProfile) {

        try {

            sessionRepository.deleteByIdProfile(idProfile);

            return Response.accepted().build();
        } catch (Exception e) {

            logger.error(String.format("Error while logging out with profile [%s]", idProfile), e);
        }

        return Response.serverError().build();
    }

    private Session createSession(ProfileEntity profileEntity) {

        SessionEntity sessionEntity = new SessionEntity();

        sessionEntity.setName(identifierGenerator.nextId(32));
        sessionEntity.setIdProfile(profileEntity.getIdProfile());
        sessionEntity.setRole(profileEntity.getRole());
        sessionEntity.setCreationDate(new Date());
        sessionEntity.setLastActionDate(new Date());

        return mapper.map(sessionRepository.save(sessionEntity), Session.class);
    }
}

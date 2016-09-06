package pfe.com.mrcore.core.service;

import pfe.com.mrcore.clientapi.dto.Profile;
import pfe.com.mrcore.clientapi.dto.Session;
import pfe.com.mrcore.clientapi.service.ProfileAPIService;
import pfe.com.mrcore.core.model.ProfileEntity;
import pfe.com.mrcore.core.service.SessionService;
import pfe.com.mrcore.core.repository.ProfileRepository;
import pfe.com.mrcore.core.utils.CustomWebExceptionHandler;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.ws.rs.core.Response;
import java.util.Date;

@Service
public class ProfileService implements ProfileAPIService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private Mapper mapper;

    private static final Integer ROLE_REQUIRED_PROFILE = 1;

    @Override
    @Transactional
    public Profile create(Profile profile) {

        Validate.notNull(profile, "Empty body [profile]");

        if (profileRepository.countByUsername(profile.getUsername()) > 0) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "USERNAME_ALREADY_EXIST");
        }

        ProfileEntity profileEntity = mapper.map(profile, ProfileEntity.class);

        profileEntity.setCreationDate(new Date());
        profileEntity.setLastModificationDate(new Date());

        return mapper.map(profileRepository.save(profileEntity), Profile.class);
    }

    @Override
    @Transactional
    public Profile update(Integer profileId, Profile profile, Session session) {

        Validate.notNull(profileId, "Missing mandatory parameter [profileId]");
        Validate.notNull(profile, "Empty body [profile]");

        sessionService.isSessionValid(session, profileId, ROLE_REQUIRED_PROFILE);

        ProfileEntity oldProfile = profileRepository.findOne(profileId);

        if (!oldProfile.getUsername().equals(profile.getUsername())
            && profileRepository.countByUsername(profile.getUsername()) > 0) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "USERNAME_ALREADY_EXIST");
        }

        ProfileEntity profileEntity = mapper.map(profile, ProfileEntity.class);

        profileEntity.setLastModificationDate(new Date());

        return mapper.map(profileRepository.save(profileEntity), Profile.class);
    }

    @Override
    @Transactional
    public Profile getProfile(Integer profileId, Session session) {

        Validate.notNull(profileId, "Missing mandatory parameter [profileId]");

        sessionService.isSessionValid(session, profileId, ROLE_REQUIRED_PROFILE);

        ProfileEntity profileEntity = profileRepository.findOne(profileId);

        return mapper.map(profileEntity, Profile.class);
    }

    @Override
    @Transactional
    public Response delete(Integer profileId, Session session) {

        Validate.notNull(profileId, "Missing mandatory parameter [profileId]");

        sessionService.isSessionValid(session, profileId, ROLE_REQUIRED_PROFILE);

        try {

            profileRepository.delete(profileId);

            return Response.accepted().build();
        } catch (Exception e) {

            return Response.serverError().build();
        }
    }
}

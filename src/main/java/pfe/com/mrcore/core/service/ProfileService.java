package pfe.com.mrcore.core.service;

import pfe.com.mrcore.clientapi.dto.profile.Goal;
import pfe.com.mrcore.clientapi.dto.profile.Profile;
import pfe.com.mrcore.clientapi.dto.profile.Sex;
import pfe.com.mrcore.clientapi.service.ProfileAPIService;
import pfe.com.mrcore.core.model.profile.GoalEntity;
import pfe.com.mrcore.core.model.profile.ProfileEntity;
import pfe.com.mrcore.core.model.profile.SexEntity;
import pfe.com.mrcore.core.repository.profile.GoalRepository;
import pfe.com.mrcore.core.repository.profile.SexRepository;
import pfe.com.mrcore.core.repository.profile.ProfileRepository;
import pfe.com.mrcore.core.utils.CustomWebExceptionHandler;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfe.com.mrcore.core.utils.DozerMapper;


import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Service
public class ProfileService implements ProfileAPIService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SexRepository sexRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private DozerMapper<Sex, SexEntity> dozerMapperSex;

    @Autowired
    private DozerMapper<Goal, GoalEntity> dozerMapperGoal;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private Mapper mapper;

    private static final Integer ROLE_REQUIRED_PROFILE = 3;

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
    public Profile update(Integer idProfile, String idSession, Profile profile) {

        if (sessionService.isSessionValid(idSession, idProfile, ROLE_REQUIRED_PROFILE)) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "SESSION_INVALID");
        }

        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");
        Validate.notNull(profile, "Empty body [profile]");

        ProfileEntity oldProfile = profileRepository.findOne(idProfile);

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
    public Profile getProfile(Integer idProfile, String idSession) {

        if (sessionService.isSessionValid(idSession, idProfile, ROLE_REQUIRED_PROFILE)) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "SESSION_INVALID");
        }

        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");

        ProfileEntity profileEntity = profileRepository.findOne(idProfile);

        return mapper.map(profileEntity, Profile.class);
    }

    @Override
    @Transactional
    public Response delete(Integer idProfile, String idSession) {

        if (sessionService.isSessionValid(idSession, idProfile, ROLE_REQUIRED_PROFILE)) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "SESSION_INVALID");
        }

        Validate.notNull(idProfile, "Missing mandatory parameter [idProfile]");

        try {

            profileRepository.delete(idProfile);

            return Response.accepted().build();
        } catch (Exception e) {

            return Response.serverError().build();
        }
    }

    @Override
    @Transactional
    public List<Sex> getSexes() {

        return dozerMapperSex.mapToDtoList(sexRepository.findAll());
    }

    @Override
    @Transactional
    public List<Goal> getGoals() {

        return dozerMapperGoal.mapToDtoList(goalRepository.findAll());
    }
}

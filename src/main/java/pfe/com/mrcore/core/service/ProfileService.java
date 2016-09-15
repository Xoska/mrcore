package pfe.com.mrcore.core.service;

import pfe.com.mrcore.clientapi.dto.geoLocation.Country;
import pfe.com.mrcore.clientapi.dto.profile.Goal;
import pfe.com.mrcore.clientapi.dto.profile.Profile;
import pfe.com.mrcore.clientapi.dto.profile.Sex;
import pfe.com.mrcore.clientapi.dto.session.Role;
import pfe.com.mrcore.clientapi.service.ProfileAPIService;
import pfe.com.mrcore.core.model.profile.ProfileEntity;
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
import pfe.com.mrcore.core.utils.InputSanitizer;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
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
    private SessionService sessionService;

    @Autowired
    private InputSanitizer inputSanitizer;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public Profile create(Profile profile) {

        Validate.notNull(profile, "Empty body [profile]");

        if (profileRepository.countByUsername(profile.getUsername()) > 0) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "USERNAME_ALREADY_EXIST");
        }

        try {

            ProfileEntity profileEntity = buildProfileEntity(profile);

            return mapper.map(profileRepository.save(profileEntity), Profile.class);
        } catch(Exception e) {


        }

        return null;
    }

    @Override
    @Transactional
    public Profile update(Integer idProfile, Profile profile) {

        Validate.notNull(profile, "Empty body [profile]");

        if (!validateProfile(profile)) {

            throw new CustomWebExceptionHandler(Response.Status.PRECONDITION_FAILED, "PROFILE_INVALID");
        }

        try {

            ProfileEntity profileEntity = profileRepository.findOne(idProfile);

            setModifiableProfileFields(profile, profileEntity);

            return mapper.map(profileRepository.save(profileEntity), Profile.class);
        } catch(Exception e) {


        }

        return null;
    }

    @Override
    public Profile getProfile(Integer idProfile) {

        try {

            ProfileEntity profileEntity = profileRepository.findOne(idProfile);

            return mapper.map(profileEntity, Profile.class);
        } catch(Exception e) {


        }

        return null;
    }

    @Override
    @Transactional
    public Response delete(@Context SecurityContext session, Integer idProfile) {

        try {

            profileRepository.delete(idProfile);
            sessionService.logout(session);

            return Response.accepted().build();
        } catch (Exception e) {


        }

        return Response.serverError().build();
    }

    @Override
    public List<Sex> getSexes() {

        try {

            return DozerMapper.map(mapper, sexRepository.findAll(), Sex.class);
        } catch(Exception e) {


        }

        return null;
    }

    @Override
    public List<Goal> getGoals() {

        try {

            return DozerMapper.map(mapper, goalRepository.findAll(), Goal.class);
        } catch(Exception e) {


        }

        return null;
    }

    private boolean validateProfile(Profile profile) {

        sanitizeFields(profile);

        return true;
    }

    private ProfileEntity buildProfileEntity(Profile profile) {

        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setRole(Role.MEMBER.getName());
        profileEntity.setCreationDate(new Date());
        profileEntity.setLastModificationDate(new Date());
        profileEntity.setUsername(profile.getUsername());

        setModifiableProfileFields(profile, profileEntity);

        return profileEntity;
    }

    private void setModifiableProfileFields(Profile profile, ProfileEntity profileEntity) {

        profileEntity.setIdSex(profile.getIdSex());
        profileEntity.setIdCity(profile.getIdCity());
        profileEntity.setIdCountry(profile.getIdCountry());
        profileEntity.setIdState(profile.getIdState());
        profileEntity.setEmail(profile.getEmail());
        profileEntity.setFirstName(profile.getFirstName());
        profileEntity.setLastName(profile.getLastName());
        profileEntity.setZipCode(profile.getZipCode());
        profileEntity.setBirthdayDate(profile.getBirthdayDate());
        profileEntity.setPassword(profile.getPassword());
    }

    private void sanitizeFields(Profile profile) {

        profile.setZipCode(inputSanitizer.cleanUnsafeString(profile.getZipCode()));
        profile.setFirstName(inputSanitizer.cleanUnsafeString(profile.getLastName()));
        profile.setLastName(inputSanitizer.cleanUnsafeString(profile.getLastName()));
        profile.setEmail(inputSanitizer.cleanUnsafeString(profile.getEmail()));
        profile.setUsername(inputSanitizer.cleanUnsafeString(profile.getUsername()));
    }
}

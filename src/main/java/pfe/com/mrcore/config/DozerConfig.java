package pfe.com.mrcore.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pfe.com.mrcore.clientapi.dto.geoLocation.City;
import pfe.com.mrcore.clientapi.dto.geoLocation.Country;
import pfe.com.mrcore.clientapi.dto.geoLocation.State;
import pfe.com.mrcore.clientapi.dto.profile.Goal;
import pfe.com.mrcore.clientapi.dto.profile.Profile;
import pfe.com.mrcore.clientapi.dto.profile.Role;
import pfe.com.mrcore.clientapi.dto.profile.Sex;
import pfe.com.mrcore.core.model.geoLocation.CityEntity;
import pfe.com.mrcore.core.model.geoLocation.CountryEntity;
import pfe.com.mrcore.core.model.geoLocation.StateEntity;
import pfe.com.mrcore.core.model.profile.GoalEntity;
import pfe.com.mrcore.core.model.profile.ProfileEntity;
import pfe.com.mrcore.core.model.profile.RoleEntity;
import pfe.com.mrcore.core.model.profile.SexEntity;

@Configuration
public class DozerConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(Role.class, RoleEntity.class);
                mapping(Profile.class, ProfileEntity.class);
                mapping(Country.class, CountryEntity.class);
                mapping(City.class, CityEntity.class);
                mapping(State.class, StateEntity.class);
                mapping(Sex.class, SexEntity.class);
                mapping(Goal.class, GoalEntity.class);
            }
        };

        return builder;
    }

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(beanMappingBuilder());

        return mapper;
    }
}

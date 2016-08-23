package pfe.com.mrcore.config;

import pfe.com.mrcore.clientapi.dto.Profile;
import pfe.com.mrcore.clientapi.dto.Role;
import pfe.com.mrcore.core.model.ProfileEntity;
import pfe.com.mrcore.core.model.RoleEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(Role.class, RoleEntity.class);
                mapping(Profile.class, ProfileEntity.class);
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

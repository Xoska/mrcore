package pfe.com.mrcore.config;

import pfe.com.mrcore.core.service.SessionService;
import pfe.com.mrcore.core.service.ProfileService;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyInitialization extends ResourceConfig {

    public JerseyInitialization() {

        register(CORSResponseFilter.class);

        register(ProfileService.class);
        register(SessionService.class);
    }
}
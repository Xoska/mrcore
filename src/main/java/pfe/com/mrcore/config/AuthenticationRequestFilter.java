package pfe.com.mrcore.config;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pfe.com.mrcore.clientapi.dto.session.Session;
import pfe.com.mrcore.core.model.session.SessionEntity;
import pfe.com.mrcore.core.repository.session.SessionRepository;
import pfe.com.mrcore.core.utils.CustomWebExceptionHandler;
import pfe.com.mrcore.core.utils.DateCalculator;
import pfe.com.mrcore.core.utils.RequiresAuthentication;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Provider
@RequiresAuthentication
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationRequestFilter implements ContainerRequestFilter {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private DateCalculator dateCalculator;

    @Autowired
    private Mapper mapper;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Map<String, Cookie> cookies = requestContext.getCookies();

        if (!cookies.containsKey("token")) {

            throw new CustomWebExceptionHandler(Response.Status.UNAUTHORIZED, "SESSION_INVALID");
        }

        String idSession = cookies.get("token").getValue();

        SessionEntity sessionEntity = sessionRepository.findOne(idSession);

        try {

            Date now = new Date();
            long hoursDifference = dateCalculator.getDateDifference(sessionEntity.getLastActionDate(), now, TimeUnit.HOURS);

            if (hoursDifference > 2) {

                sessionRepository.delete(sessionEntity);
                throw new CustomWebExceptionHandler(Response.Status.UNAUTHORIZED, "SESSION_EXPIRED");
            }
            else {

                sessionEntity.setLastActionDate(now);
                sessionRepository.save(sessionEntity);
            }
        }
        catch (Exception e) {

            throw new CustomWebExceptionHandler(Response.Status.UNAUTHORIZED, "SESSION_INVALID");
        }

        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {

                return mapper.map(sessionEntity, Session.class);
            }

            @Override
            public boolean isUserInRole(String role) {

                return sessionEntity.getRole().equals(role);
            }

            @Override
            public boolean isSecure() {

                return false;
            }

            @Override
            public String getAuthenticationScheme() {

                return SecurityContext.BASIC_AUTH;
            }
        });
    }
}

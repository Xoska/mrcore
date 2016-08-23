package pfe.com.mrcore.core.utils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CustomWebExceptionHandler extends WebApplicationException {

    public CustomWebExceptionHandler(Response.Status status, String message) {

        super(Response.status(status).entity(message).type("text/plain").build());
    }
}
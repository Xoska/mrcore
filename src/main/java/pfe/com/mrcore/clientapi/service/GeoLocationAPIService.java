package pfe.com.mrcore.clientapi.service;

import org.springframework.cache.annotation.Cacheable;
import pfe.com.mrcore.clientapi.dto.geoLocation.City;
import pfe.com.mrcore.clientapi.dto.geoLocation.Country;
import pfe.com.mrcore.clientapi.dto.geoLocation.State;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/geoLocation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GeoLocationAPIService {

    @GET
    @Path("/countries")
    @Cacheable(value = "countries")
    List<Country> getCountries();

    @GET
    @Path("/cities")
    @Cacheable(value = "cities")
    List<City> getCities(@QueryParam("id_state") Integer idState);

    @GET
    @Path("/states")
    @Cacheable(value = "states")
    List<State> getStates(@QueryParam("id_country") Integer idCountry);

}

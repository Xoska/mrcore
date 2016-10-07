package pfe.com.mrcore.core.service;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.com.mrcore.clientapi.dto.geoLocation.City;
import pfe.com.mrcore.clientapi.dto.geoLocation.Country;
import pfe.com.mrcore.clientapi.dto.geoLocation.State;
import pfe.com.mrcore.clientapi.service.GeoLocationAPIService;
import pfe.com.mrcore.core.repository.geoLocation.CityRepository;
import pfe.com.mrcore.core.repository.geoLocation.CountryRepository;
import pfe.com.mrcore.core.repository.geoLocation.StateRepository;
import pfe.com.mrcore.core.utils.DozerMapper;

import java.util.List;

@Service
public class GeoLocationService implements GeoLocationAPIService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private Mapper mapper;

    private final static Logger logger = Logger.getLogger(GeoLocationService.class);

    @Override
    public List<Country> getCountries() {

        try {

            return DozerMapper.map(mapper, countryRepository.findAll(), Country.class);
        } catch(Exception e) {

            logger.error("Error while getting the countries", e);
        }

        return null;
    }

    @Override
    public List<State> getStates(Integer idCountry) {

        Validate.notNull(idCountry, "Missing mandatory parameter [idCountry]");

        try {

            return DozerMapper.map(mapper, stateRepository.findAllByIdCountry(idCountry), State.class);
        } catch(Exception e) {

            logger.error(String.format("Error while searching for states with country [%s]", idCountry), e);
        }

        return null;
    }

    @Override
    public List<City> getCities(Integer idState) {

        Validate.notNull(idState, "Missing mandatory parameter [idState]");

        try {

            return DozerMapper.map(mapper, cityRepository.findAllByIdState(idState), City.class);
        } catch(Exception e) {

            logger.error(String.format("Error while searching for citites with state [%s]", idState), e);
        }

        return null;
    }
}

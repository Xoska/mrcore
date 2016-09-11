package pfe.com.mrcore.core.service;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfe.com.mrcore.clientapi.dto.geoLocation.City;
import pfe.com.mrcore.clientapi.dto.geoLocation.Country;
import pfe.com.mrcore.clientapi.dto.geoLocation.State;
import pfe.com.mrcore.clientapi.service.GeoLocationAPIService;
import pfe.com.mrcore.core.model.geoLocation.CityEntity;
import pfe.com.mrcore.core.model.geoLocation.CountryEntity;
import pfe.com.mrcore.core.model.geoLocation.StateEntity;
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
    private DozerMapper<Country, CountryEntity> dozerMapperCountry;

    @Autowired
    private DozerMapper<State, StateEntity> dozerMapperState;

    @Autowired
    private DozerMapper<City, CityEntity> dozerMapperCity;

    private static final Integer ROLE_REQUIRED_PROFILE = 3;

    @Override
    @Transactional
    public List<Country> getCountries() {

        return dozerMapperCountry.mapToDtoList(countryRepository.findAll());
    }

    @Override
    @Transactional
    public List<State> getStates(Integer idCountry) {

        return dozerMapperState.mapToDtoList(stateRepository.findAllByIdCountry(idCountry));
    }

    @Override
    @Transactional
    public List<City> getCities(Integer idState) {

        Validate.notNull(idState, "Missing mandatory parameter [idState]");

        return dozerMapperCity.mapToDtoList(cityRepository.findAllByIdState(idState));
    }
}

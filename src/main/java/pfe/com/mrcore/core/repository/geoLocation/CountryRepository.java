package pfe.com.mrcore.core.repository.geoLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.geoLocation.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {

}

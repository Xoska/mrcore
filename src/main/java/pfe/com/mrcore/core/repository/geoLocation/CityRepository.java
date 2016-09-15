package pfe.com.mrcore.core.repository.geoLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.geoLocation.CityEntity;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    List<CityEntity> findAllByIdState(Integer idState);
}

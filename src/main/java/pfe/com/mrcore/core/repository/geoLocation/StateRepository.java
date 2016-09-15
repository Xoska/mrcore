package pfe.com.mrcore.core.repository.geoLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.geoLocation.StateEntity;

import java.util.List;

public interface StateRepository extends JpaRepository<StateEntity, Integer> {

    List<StateEntity> findAllByIdCountry(Integer idCountry);
}

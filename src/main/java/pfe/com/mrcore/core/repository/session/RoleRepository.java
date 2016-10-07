package pfe.com.mrcore.core.repository.session;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.profile.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}

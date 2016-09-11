package pfe.com.mrcore.core.repository.profile;

import pfe.com.mrcore.core.model.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    ProfileEntity findByUsernameAndPassword(String username, String password);
    Integer countByUsername(String username);
}

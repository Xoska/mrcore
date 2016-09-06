package pfe.com.mrcore.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.SessionEntity;

public interface SessionRepository extends JpaRepository<SessionEntity, String> {

    SessionEntity findByIdProfile(Integer idProfile);
}

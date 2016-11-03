package pfe.com.mrcore.core.repository.session;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.session.SessionEntity;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, String> {

    SessionEntity findByIdProfile(Integer idProfile);
}

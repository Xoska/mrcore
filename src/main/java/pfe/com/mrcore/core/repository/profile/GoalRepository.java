package pfe.com.mrcore.core.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.profile.GoalEntity;

public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {

}

package pfe.com.mrcore.core.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import pfe.com.mrcore.core.model.chat.QueueEntity;
import pfe.com.mrcore.core.model.profile.GoalEntity;

public interface QueueRepository extends JpaRepository<QueueEntity, String> {

    QueueEntity findByIdProfile(Integer idProfile);
}

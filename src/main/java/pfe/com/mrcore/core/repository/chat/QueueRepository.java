package pfe.com.mrcore.core.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfe.com.mrcore.core.model.chat.QueueEntity;
import pfe.com.mrcore.core.model.profile.GoalEntity;

import java.util.List;

public interface QueueRepository extends JpaRepository<QueueEntity, String> {

    Integer countByIdProfile(Integer idProfile);
    Integer deleteByIdProfile(Integer idProfile);

    @Query("  SELECT q FROM QueueEntity q WHERE\n" +
            " q.idCity in (:idsCitySearch) AND :idCity in (q.idsCitySearch) AND\n" +
            " q.idCountry = :idCountrySearch AND q.idCountrySearch = :idCountry AND\n" +
            " q.idState = :idStateSearch AND q.idStateSearch = :idState AND\n" +
            " q.idSex = :idSexSearch AND q.idSexSearch = :idSex AND\n" +
            " q.idGoal = :idGoal AND\n" +
            " q.age >= :ageSearchMin AND q.age <= :ageSearchMax AND\n" +
            " q.ageMinSearch <= :age AND q.ageMaxSearch >= :age")
    QueueEntity searchMatch(@Param("idsCitySearch") List<Integer> idsCitySearch, @Param("idCity") Integer idCity,
                            @Param("idCountrySearch") Integer idCountrySearch, @Param("idCountry") Integer idCountry,
                            @Param("idStateSearch") Integer idStateSearch, @Param("idState") Integer idState,
                            @Param("idSexSearch") Integer idSexSearch, @Param("idSex") Integer idSex,
                            @Param("idGoal") Integer idGoal,
                            @Param("ageSearchMin") Integer ageSearchMin, @Param("ageSearchMax") Integer ageSearchMax,
                            @Param("age") Integer age);
}

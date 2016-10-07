package pfe.com.mrcore.core.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfe.com.mrcore.core.model.chat.QueueEntity;

import java.util.List;

public interface QueueRepository extends JpaRepository<QueueEntity, Integer> {

    Integer countByIdProfile(Integer idProfile);
    void deleteByIdProfile(Integer idProfile);

    @Query("  SELECT q FROM QueueEntity q WHERE " +
            " (0 in (:idsCitySearch) OR q.idCity in (:idsCitySearch)) AND " +
            " (0 in elements(q.idsCitySearch) OR :idCity in elements(q.idsCitySearch)) AND " +
            " (:idCountrySearch = 0 OR q.idCountry = :idCountrySearch) AND " +
            " (q.idCountrySearch = 0 OR q.idCountrySearch = :idCountry) AND " +
            " (:idStateSearch = 0 OR q.idState = :idStateSearch) AND " +
            " (q.idStateSearch = 0 OR q.idStateSearch = :idState) AND " +
            " (:idSexSearch = 0 OR q.idSex = :idSexSearch) AND " +
            " (q.idSexSearch = 0 OR q.idSexSearch = :idSex) AND " +
            " q.idGoal = :idGoal AND " +
            " q.age >= :ageSearchMin AND " +
            " q.age <= :ageSearchMax AND " +
            " q.ageMinSearch <= :age AND " +
            " q.ageMaxSearch >= :age " +
            " ORDER BY q.creationDate ")
    List<QueueEntity> searchMatch(@Param("idsCitySearch") List<Integer> idsCitySearch, @Param("idCity") Integer idCity,
                                  @Param("idCountrySearch") Integer idCountrySearch, @Param("idCountry") Integer idCountry,
                                  @Param("idStateSearch") Integer idStateSearch, @Param("idState") Integer idState,
                                  @Param("idSexSearch") Integer idSexSearch, @Param("idSex") Integer idSex,
                                  @Param("idGoal") Integer idGoal,
                                  @Param("ageSearchMin") Integer ageSearchMin, @Param("ageSearchMax") Integer ageSearchMax,
                                  @Param("age") Integer age);
}

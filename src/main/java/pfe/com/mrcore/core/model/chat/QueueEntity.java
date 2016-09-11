package pfe.com.mrcore.core.model.chat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profile")
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_queue")
    private Integer idQueue;

    @Column(name = "id_profile")
    private Integer idProfile;

    @Column(name = "id_city")
    private List<Integer> idCities;

    @Column(name = "id_country")
    private Integer idCountry;

    @Column(name = "is_state")
    private Integer idState;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "id_sex")
    private Integer idSex;

    @Column(name = "id_sex_search")
    private Integer idSexSearch;

    @Column(name = "id_goal")
    private Integer idGoal;

    @Column(name = "id_room")
    private String idRoom;

    @Column(name = "age")
    private Integer age;

    @Column(name = "age_min_search")
    private Integer ageMinSearch;

    @Column(name = "age_max_search")
    private Integer ageMaxSearch;

    @Column(name = "creation_date")
    private Date creationDate;

    public Integer getIdQueue() {
        return idQueue;
    }

    public void setIdQueue(Integer idQueue) {
        this.idQueue = idQueue;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getIdSex() {
        return idSex;
    }

    public void setIdSex(Integer idSex) {
        this.idSex = idSex;
    }

    public Integer getIdSexSearch() {
        return idSexSearch;
    }

    public void setIdSexSearch(Integer idSexSearch) {
        this.idSexSearch = idSexSearch;
    }

    public Integer getIdGoal() {
        return idGoal;
    }

    public void setIdGoal(Integer idGoal) {
        this.idGoal = idGoal;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeMinSearch() {
        return ageMinSearch;
    }

    public void setAgeMinSearch(Integer ageMinSearch) {
        this.ageMinSearch = ageMinSearch;
    }

    public Integer getAgeMaxSearch() {
        return ageMaxSearch;
    }

    public void setAgeMaxSearch(Integer ageMaxSearch) {
        this.ageMaxSearch = ageMaxSearch;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}

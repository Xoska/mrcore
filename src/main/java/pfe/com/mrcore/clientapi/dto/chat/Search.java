package pfe.com.mrcore.clientapi.dto.chat;

import java.util.List;

public class Search {

    private Integer idSex;
    private Integer idGoal;
    private Integer idCountry;
    private Integer idState;
    private List<Integer> idsCity;
    private Integer ageMin;
    private Integer ageMax;

    public Integer getIdSex() {
        return idSex;
    }

    public void setIdSex(Integer idSex) {
        this.idSex = idSex;
    }

    public Integer getIdGoal() {
        return idGoal;
    }

    public void setIdGoal(Integer idGoal) {
        this.idGoal = idGoal;
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

    public List<Integer> getIdsCity() {
        return idsCity;
    }

    public void setIdsCity(List<Integer> idsCity) {
        this.idsCity = idsCity;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }
}

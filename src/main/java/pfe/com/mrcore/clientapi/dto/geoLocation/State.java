package pfe.com.mrcore.clientapi.dto.geoLocation;

import java.io.Serializable;

public class State implements Serializable {

    private Integer idState;
    private Country country;
    private String name;

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

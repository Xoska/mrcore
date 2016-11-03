package pfe.com.mrcore.clientapi.dto.geoLocation;

import java.io.Serializable;

public class State implements Serializable {

    private Integer idState;
    private Integer idCountry;
    private String name;

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

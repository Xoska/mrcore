package pfe.com.mrcore.clientapi.dto.geoLocation;

import java.io.Serializable;

public class City implements Serializable {

    private Integer idCity;
    private State state;
    private String name;

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

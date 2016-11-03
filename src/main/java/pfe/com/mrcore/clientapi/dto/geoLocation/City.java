package pfe.com.mrcore.clientapi.dto.geoLocation;

import java.io.Serializable;

public class City implements Serializable {

    private Integer idCity;
    private Integer idState;
    private String name;

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

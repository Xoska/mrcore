package pfe.com.mrcore.clientapi.dto.profile;

import java.io.Serializable;

public class Sex implements Serializable {

    private Integer idSex;
    private String name;

    public Integer getIdSex() {
        return idSex;
    }

    public void setIdSex(Integer idSex) {
        this.idSex = idSex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

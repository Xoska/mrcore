package pfe.com.mrcore.clientapi.dto.profile;

import java.io.Serializable;

public class Role implements Serializable {

    private Integer idRole;
    private String name;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

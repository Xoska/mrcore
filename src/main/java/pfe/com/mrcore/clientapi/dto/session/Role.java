package pfe.com.mrcore.clientapi.dto.session;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMINISTRATOR(1, "ADMINISTRATOR"),
    PRIVILEGED_MEMBER(2, "PRIVILEGED_MEMBER"),
    MEMBER(3, "MEMBER"),
    ANONYMOUS(4, "ANONYMOUS");

    private Integer idRole;
    private String name;

    private Role(Integer idRole, String name) {

        this.idRole = idRole;
        this.name = name;
    }

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

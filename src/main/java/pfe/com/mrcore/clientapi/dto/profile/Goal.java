package pfe.com.mrcore.clientapi.dto.profile;

import java.io.Serializable;

public class Goal implements Serializable {

    private Integer idGoal;
    private String name;

    public Integer getIdGoal() {
        return idGoal;
    }

    public void setIdGoal(Integer idGoal) {
        this.idGoal = idGoal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package pfe.com.mrcore.core.model.profile;

import javax.persistence.*;

@Entity
@Table(name = "goal")
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_goal")
    private Integer idGoal;

    @Column(name = "name")
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

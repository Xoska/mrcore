package pfe.com.mrcore.core.model.profile;

import javax.persistence.*;

@Entity
@Table(name = "sex")
public class SexEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sex")
    private Integer idSex;

    @Column(name = "name")
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

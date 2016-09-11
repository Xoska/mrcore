package pfe.com.mrcore.core.model.geoLocation;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_city")
    private Integer idCity;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_state")
    private StateEntity state;

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }
}

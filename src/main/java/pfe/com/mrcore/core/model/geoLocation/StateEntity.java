package pfe.com.mrcore.core.model.geoLocation;

import javax.persistence.*;

@Entity
@Table(name = "state")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_state")
    private Integer idState;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_country")
    private CountryEntity country;

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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}

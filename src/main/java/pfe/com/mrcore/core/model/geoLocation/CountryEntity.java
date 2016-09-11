package pfe.com.mrcore.core.model.geoLocation;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_country")
    private Integer idCountry;

    @Column(name = "name")
    private String name;

    @Column(name = "sortname")
    private String sortname;

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }
}

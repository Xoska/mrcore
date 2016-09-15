package pfe.com.mrcore.core.model.session;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "session")
public class SessionEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "id_profile")
    private Integer idProfile;

    @Column(name = "role")
    private String role;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_action_date")
    private Date lastActionDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(Date lastActionDate) {
        this.lastActionDate = lastActionDate;
    }
}
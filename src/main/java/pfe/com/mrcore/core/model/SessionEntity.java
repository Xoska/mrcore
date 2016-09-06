package pfe.com.mrcore.core.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "session")
public class SessionEntity {

    @Id
    @Column(name = "id_session")
    private String idSession;

    @Column(name = "id_profile")
    private Integer idProfile;

    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_action_date")
    private Date lastActionDate;

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
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
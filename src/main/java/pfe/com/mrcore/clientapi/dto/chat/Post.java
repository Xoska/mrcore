package pfe.com.mrcore.clientapi.dto.chat;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    private Integer idPost;
    private Integer idProfile;
    private Date date;
    private String content;

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

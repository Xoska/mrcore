package pfe.com.mrcore.clientapi.dto.chat;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    private Integer idPost;
    private Integer idProfile;
    private String content;
    private String username;
    private String typePost;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTypePost() {
        return typePost;
    }

    public void setTypePost(String typePost) {
        this.typePost = typePost;
    }
}

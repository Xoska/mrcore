package pfe.com.mrcore.clientapi.dto.chat;

import java.io.Serializable;

public enum TypePost implements Serializable {

    MESSAGE(1, "MESSAGE"),
    NOTIFICATION_ENTER_ROOM(2, "NOTIFICATION_ENTER_ROOM"),
    NOTIFICATION_LEAVE_ROOM(3, "NOTIFICATION_LEAVE_ROOM");

    private Integer idTypePost;
    private String name;

    private TypePost(Integer idTypePost, String name) {

        this.idTypePost = idTypePost;
        this.name = name;
    }

    public Integer getIdTypePost() {
        return idTypePost;
    }

    public void setIdTypePost(Integer idTypePost) {
        this.idTypePost = idTypePost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

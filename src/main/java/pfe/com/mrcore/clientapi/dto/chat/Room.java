package pfe.com.mrcore.clientapi.dto.chat;

import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {

    private String idRoom;
    private Date enteredDate;
    private String owner;

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

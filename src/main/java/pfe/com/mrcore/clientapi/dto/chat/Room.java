package pfe.com.mrcore.clientapi.dto.chat;

import java.util.Date;

public class Room {

    private String idRoom;
    private Date enteredDate;

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
}

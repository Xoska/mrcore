package pfe.com.mrcore.clientapi.dto;

import java.util.Date;

public class Message {

    private Integer idSender;
    private Integer idReceiver;
    private String message;
    private Date sentDate;

    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }

    public Integer getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Integer idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
}

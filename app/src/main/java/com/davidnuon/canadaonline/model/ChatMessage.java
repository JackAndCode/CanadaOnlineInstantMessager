package com.davidnuon.canadaonline.model;

import java.util.Date;

/**
 * Created by davidnuon on 12/13/14.
 */
public class ChatMessage {
    String message;
    String language;
    Date timestamp;
    String sender;

    public  ChatMessage () {

    }

    public ChatMessage(String sender, String message, String language, Date timestamp) {
        this.message = message;
        this.language = language;
        this.timestamp = timestamp;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

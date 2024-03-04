package com.example.siteliconPrueba;

import java.security.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class ContactEntity {

    private @Id @GeneratedValue Integer contactID;

    private String name;
    private List<String> reason;
    private String email;
    private String message;
    private Timestamp timestamp;

    protected ContactEntity() {
        this(null, null, null, null);
    }

    ContactEntity(String name, String message, String email, List<String> reason) {
        this.contactID = null;
        this.name = name;
        this.message = message;
        this.email = email;
        this.reason = reason;
    }

    ContactEntity(String name) {
        this.contactID = null;
        this.name = name;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getReason() {
        return reason;
    }

    public void setReason(List<String> reason) {
        this.reason = reason;
    }

    public Timestamp getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timestamp = timeStamp;
    }

}

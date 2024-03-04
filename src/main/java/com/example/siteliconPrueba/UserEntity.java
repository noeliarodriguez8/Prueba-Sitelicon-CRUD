package com.example.siteliconPrueba;

import java.security.Timestamp;
//import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class UserEntity {

    private @Id @GeneratedValue Integer userID;

    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Timestamp timestamp;

    protected UserEntity() {
        this(null, null, null, null, null);
    }

    UserEntity(String name, String lastName, String phoneNumber, String email, String password) {
        this.userID = null;
        this.lastName = lastName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    UserEntity(String name) {
        this.userID = null;
        this.name = name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timestamp = timeStamp;
    }

}

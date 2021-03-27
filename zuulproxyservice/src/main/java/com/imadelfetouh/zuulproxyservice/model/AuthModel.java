package com.imadelfetouh.zuulproxyservice.model;

import java.io.Serializable;

public class AuthModel implements Serializable {

    private int userId;
    private String username;
    private String photo;

    public AuthModel() {

    }

    public AuthModel(int userId, String username, String photo) {
        this.userId = userId;
        this.username = username;
        this.photo = photo;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }
}

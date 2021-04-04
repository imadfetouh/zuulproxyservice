package com.imadelfetouh.zuulproxyservice.model;

import java.io.Serializable;

public class AuthModel implements Serializable {

    private String userId;
    private String username;
    private String photo;
    private String role;

    public AuthModel() {

    }

    public AuthModel(String userId, String username, String photo, String role) {
        this.userId = userId;
        this.username = username;
        this.photo = photo;
        this.role = role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
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

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

package com.app.restaurant0.model;

public class RenameFB {
    String username, phone, email;

    public RenameFB(String username, String phone, String email) {
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    public RenameFB() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
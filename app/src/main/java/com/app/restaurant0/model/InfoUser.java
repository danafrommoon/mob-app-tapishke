package com.app.restaurant0.model;

public class InfoUser {

    String imageurl, username, phone, email, info;

    public InfoUser(String imageurl, String username, String phone, String email, String info) {
        this.imageurl = imageurl;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.info = info;
    }

    public InfoUser() {

    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}



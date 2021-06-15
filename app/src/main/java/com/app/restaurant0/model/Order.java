package com.app.restaurant0.model;

public class Order {

    String username;
    String restOrd;
    String tabletNumOrd;
    String timeOrd;
    String guestsOrd;
    String statusOrd;

    public Order() {}

    public Order(String username, String restOrd, String tabletNumOrd, String timeOrd, String guestsOrd, String statusOrd) {
        this.username = username;
        this.restOrd = restOrd;
        this.tabletNumOrd = tabletNumOrd;
        this.timeOrd = timeOrd;
        this.guestsOrd = guestsOrd;
        this.statusOrd = statusOrd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestOrd() {
        return restOrd;
    }

    public void setRestOrd(String restOrd) {
        this.restOrd = restOrd;
    }

    public String getTabletNumOrd() {
        return tabletNumOrd;
    }

    public void setTabletNumOrd(String tabletNumOrd) {
        this.tabletNumOrd = tabletNumOrd;
    }

    public String getTimeOrd() {
        return timeOrd;
    }

    public void setTimeOrd(String timeOrd) {
        this.timeOrd = timeOrd;
    }

    public String getGuestsOrd() {
        return guestsOrd;
    }

    public void setGuestsOrd(String guestsOrd) {
        this.guestsOrd = guestsOrd;
    }

    public String getStatusOrd() {
        return statusOrd;
    }

    public void setStatusOrd(String statusOrd) {
        this.statusOrd = statusOrd;
    }
}

package com.app.restaurant0.model;

public class InfoNews {

    String imgNews, infoNews, descriptionNews;

    public InfoNews() {

    }

    public InfoNews(String imgNews, String infoNews, String descriptionNews) {
        this.imgNews = imgNews;
        this.infoNews = infoNews;
        this.descriptionNews = descriptionNews;
    }

    public String getImgNews() {
        return imgNews;
    }

    public void setImgNews(String imgNews) {
        this.imgNews = imgNews;
    }

    public String getInfoNews() {
        return infoNews;
    }

    public void setInfoNews(String infoNews) {
        this.infoNews = infoNews;
    }

    public String getDescriptionNews() {
        return descriptionNews;
    }

    public void setDescriptionNews(String descriptionNews) {
        this.descriptionNews = descriptionNews;
    }
}
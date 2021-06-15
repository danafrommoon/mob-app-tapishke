package com.app.restaurant0.model;

public class InfoRes {

    String imgRes, nameRes, phoneRes, streetRes, priceRes, btnRes, descriptionRes;

    public InfoRes() {}

    public InfoRes(String imgRes, String nameRes, String phoneRes, String streetRes, String priceRes, String btnRes, String descriptionRes) {
        this.imgRes = imgRes;
        this.nameRes = nameRes;
        this.phoneRes = phoneRes;
        this.streetRes = streetRes;
        this.priceRes = priceRes;
        this.btnRes = btnRes;
        this.descriptionRes = descriptionRes;
    }

    public String getImgRes() {
        return imgRes;
    }

    public void setImgRes(String imgRes) {
        this.imgRes = imgRes;
    }

    public String getNameRes() {
        return nameRes;
    }

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    public String getPhoneRes() {
        return phoneRes;
    }

    public void setPhoneRes(String phoneRes) {
        this.phoneRes = phoneRes;
    }

    public String getStreetRes() {
        return streetRes;
    }

    public void setStreetRes(String streetRes) {
        this.streetRes = streetRes;
    }

    public String getPriceRes() {
        return priceRes;
    }

    public void setPriceRes(String priceRes) {
        this.priceRes = priceRes;
    }

    public String getBtnRes() {
        return btnRes;
    }

    public void setBtnRes(String btnRes) {
        this.btnRes = btnRes;
    }

    public String getDescriptionRes() {
        return descriptionRes;
    }

    public void setDescriptionRes(String descriptionRes) {
        this.descriptionRes = descriptionRes;
    }
}
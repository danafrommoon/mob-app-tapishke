package com.app.restaurant0.model;

import android.widget.RatingBar;

public class Model {
    String name, feedback;
    RatingBar ratingBar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }

    public Model(String name, String feedback, RatingBar ratingBar) {
        this.name = name;
        this.feedback = feedback;
        this.ratingBar = ratingBar;
    }
}

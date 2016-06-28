package com.example.sesipuser.travelbuudy.api.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SESIP User on 20-Nov-15.
 */
public class SpotDescriptionModel {

    @SerializedName("TEXT")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

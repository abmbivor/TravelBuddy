package com.example.sesipuser.travelbuudy.api.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SESIP User on 20-Nov-15.
 */
public class TouristSpotModel {

    @SerializedName("TOURIST_SPOT_NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

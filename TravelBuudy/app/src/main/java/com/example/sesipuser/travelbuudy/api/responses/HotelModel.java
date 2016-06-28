package com.example.sesipuser.travelbuudy.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by SESIP User on 20-Nov-15.
 */
public class HotelModel {

    @SerializedName("HOTEL_NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

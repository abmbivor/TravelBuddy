package com.example.sesipuser.travelbuudy.api.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SESIP User on 19-Nov-15.
 */
public class TransportModel {

    @SerializedName("TRANSPORT_NAME")
    private String name;

    @SerializedName("TRANSPORT_TYPE")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

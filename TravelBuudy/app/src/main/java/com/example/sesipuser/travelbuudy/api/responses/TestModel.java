package com.example.sesipuser.travelbuudy.api.responses;


import com.google.gson.annotations.SerializedName;

/**
 * Created by SESIP User on 11-Nov-15.
 */
public class TestModel {

    @SerializedName("success")
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}

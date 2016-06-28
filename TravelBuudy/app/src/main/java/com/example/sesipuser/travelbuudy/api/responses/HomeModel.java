package com.example.sesipuser.travelbuudy.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SESIP User on 15-Nov-15.
 */
public class HomeModel {
    @SerializedName("districts")
//    private List<String> districts=new ArrayList<String>();
//
//    public List<String> getDistricts() {
//        return districts;
//    }
//
//    public void setDistricts(List<String> districts) {
//        this.districts = districts;
//    }
        private ArrayList<String> districts;

    public ArrayList<String> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<String> districts) {
        this.districts = districts;
    }
}

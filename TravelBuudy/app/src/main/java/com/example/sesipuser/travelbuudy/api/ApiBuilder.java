package com.example.sesipuser.travelbuudy.api;

import retrofit.RestAdapter;

/**
 * Created by SESIP User on 11-Nov-15.
 */
public class ApiBuilder {

    public static ApiEndPoints build() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint("http://169.254.111.30/ci/index.php");
        //builder.setEndpoint("http://59.152.96.51/ci/index.php");
        RestAdapter adapter = builder.build();
        return adapter.create(ApiEndPoints.class);
    }
}

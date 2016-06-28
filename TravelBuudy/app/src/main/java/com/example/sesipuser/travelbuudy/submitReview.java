package com.example.sesipuser.travelbuudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.HomeModel;
import com.example.sesipuser.travelbuudy.api.responses.SubmitReviewModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.R.layout.simple_spinner_dropdown_item;

/**
 * Created by SESIP User on 07-Nov-15.
 */
public class submitReview extends Activity {
    public static final String EXTRA_USERNAME="EXTRA_USERNAME";

    private String emnei="bb";
    ArrayList<String> districts = new ArrayList<>();
    ArrayList<String> spots = new ArrayList<>();
    private ArrayAdapter<String> mSourceAdapter;
    private Spinner mSourceSpinner;
    private String mCity;
    private Spinner mSpotSpinner;
    private ArrayAdapter<String> mSpotAdapter;
    private String city;
    private String spot;
    private String username;
    private String review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submitreview);
        final ApiEndPoints api = ApiBuilder.build();


        api.home(emnei, new Callback<HomeModel>() {
            @Override
            public void success(HomeModel homeModel, Response response) {

                districts = homeModel.getDistricts();
                mSourceAdapter.clear();
                mSourceAdapter.addAll(districts);
                mSourceAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),
                        "Server Down", Toast.LENGTH_LONG).show();

            }
        });

        mSourceSpinner = (Spinner) findViewById(R.id.citySpinner);
        mSourceAdapter = new ArrayAdapter<>(submitReview.this, simple_spinner_dropdown_item, districts);
        mSourceSpinner.setAdapter(mSourceAdapter);


        mSpotSpinner= (Spinner) findViewById(R.id.spotSpinner);
        mSpotAdapter = new ArrayAdapter<>(submitReview.this,simple_spinner_dropdown_item,spots);
        mSpotSpinner.setAdapter(mSpotAdapter);

        mSourceSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {

            final ApiEndPoints api=ApiBuilder.build();
             mCity= parent.getItemAtPosition(pos).toString();

            api.getSpot(mCity, new Callback<HomeModel>() {
                @Override
                public void success(HomeModel homeModel, Response response) {

                    spots = homeModel.getDistricts();
                    mSpotAdapter.clear();
                    mSpotAdapter.addAll(spots);
                    mSpotAdapter.notifyDataSetChanged();

                }

                @Override
                public void failure(RetrofitError error) {

                    Toast.makeText(getApplicationContext(),
                            "Server Down", Toast.LENGTH_LONG).show();
                }
            });
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }

    }



    public void submitReview(View v){

        city= mSourceSpinner.getSelectedItem().toString();
        spot= mSpotSpinner.getSelectedItem().toString();
        username= getIntent().getStringExtra(EXTRA_USERNAME);
        review= ((EditText) findViewById(R.id.reviewText)).getText().toString();
        final ApiEndPoints api=ApiBuilder.build();
        api.submitReview(spot,review,username, new Callback<SubmitReviewModel>() {
            @Override
            public void success(SubmitReviewModel submitReviewModel, Response response) {

                String t = submitReviewModel.getSuccess();
                if (t.equals("1")) {
                    Toast.makeText(submitReview.this, "Review Submitted Successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),home.class);
                    intent.putExtra(home.EXTRA_USERNAME,username);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getApplicationContext(),
                        "Server Down", Toast.LENGTH_LONG).show();
            }
        });

    }
}

package com.example.sesipuser.travelbuudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.HomeModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.R.layout.simple_spinner_dropdown_item;

/**
 * Created by SESIP User on 07-Nov-15.
 */
public class home extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";

    //Spinner spinner;
    ArrayList<String> districts = new ArrayList<>();
    String emnei = "bb";
    private ArrayAdapter<String> mSourceAdapter;
    private ArrayAdapter<String> mDestinationAdapter;
    private Spinner mSourceSpinner;
    private Spinner mDestinationSpinner;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        final ApiEndPoints api = ApiBuilder.build();


        api.home(emnei, new Callback<HomeModel>() {
            @Override
            public void success(HomeModel homeModel, Response response) {
                //List<String> districts=new ArrayList<String>();
                districts = homeModel.getDistricts();
                mSourceAdapter.clear();
                mSourceAdapter.addAll(districts);
                mSourceAdapter.notifyDataSetChanged();

                mDestinationAdapter.clear();
                mDestinationAdapter.addAll(districts);
                mDestinationAdapter.notifyDataSetChanged();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),
                        "Server Down", Toast.LENGTH_LONG).show();

            }
        });


        mSourceSpinner = (Spinner) findViewById(R.id.sourcespinner);
        //ArrayAdapter adapter=ArrayAdapter.createFromResource(home.this,R.array.source_list, android.R.layout.simple_spinner_dropdown_item);
        mSourceAdapter = new ArrayAdapter<>(home.this, simple_spinner_dropdown_item, districts);
        mSourceSpinner.setAdapter(mSourceAdapter);
        mDestinationSpinner = (Spinner) findViewById(R.id.destspinner);
        mDestinationAdapter = new ArrayAdapter<>(home.this, simple_spinner_dropdown_item, districts);
        //ArrayAdapter adapter2=ArrayAdapter.createFromResource(home.this, R.array.source_list, android.R.layout.simple_spinner_dropdown_item);
        mDestinationSpinner.setAdapter(mDestinationAdapter);


    }

    public void buttonOnClick(View v) {
        //Button button=(Button) v;
        if(mSourceSpinner.getSelectedItem().toString().equals(mDestinationSpinner.getSelectedItem().toString()))
        {
            Toast.makeText(getApplicationContext(),
                    "Source & Destination Selected are same !!!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), destination.class);
            intent.putExtra(destination.EXTRA_SOURCE, mSourceSpinner.getSelectedItem().toString());
            intent.putExtra(destination.EXTRA_DESTINATION, mDestinationSpinner.getSelectedItem().toString());
            startActivity(intent);
            //finish();
        }
    }

    public void reviewbutton1(View v) {
        username= getIntent().getStringExtra(EXTRA_USERNAME);
        Intent intent = new Intent(getApplicationContext(),submitReview.class);
        intent.putExtra(submitReview.EXTRA_USERNAME,username);
        startActivity(intent);
        //finish();
    }

    public void forumbutton1(View v) {
        username= getIntent().getStringExtra(EXTRA_USERNAME);
        Intent intent = new Intent(getApplicationContext(),forumPost.class);
        intent.putExtra(forumPost.EXTRA_USERNAME,username);
        startActivity(intent);
    }
}


package com.example.sesipuser.travelbuudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by SESIP User on 07-Nov-15.
 */
public class destination extends AppCompatActivity {
    public static final String EXTRA_SOURCE = "EXTRA_SOURCE";
    public static final String EXTRA_DESTINATION = "EXTRA_DESTINATION";
    private String mSource;
    private String mDestination;
    private ViewPager mPager;
    private DestinationDetailsPageAdapter mPagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destination);
        mSource = getIntent().getStringExtra(EXTRA_SOURCE);
        mDestination = getIntent().getStringExtra(EXTRA_DESTINATION);
        setTitle(mSource+" - "+mDestination);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new DestinationDetailsPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);



    }

    public class DestinationDetailsPageAdapter extends FragmentPagerAdapter {

        public static final int NUM_PAGES = 2;

        public DestinationDetailsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return TransportFragment.newInstance(mSource,mDestination);
                case 1:
                    return HotelFragment.newInstance(mDestination);
            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Available Transports";
                case 1:
                    return "Available Hotels";
            }

            return "";
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public void seeSpots(View v){
        Intent intent= new Intent(getApplicationContext(),touristspots.class);
        intent.putExtra(touristspots.EXTRA_DESTINATION,mDestination);
        startActivity(intent);
       // finish();
    }
}
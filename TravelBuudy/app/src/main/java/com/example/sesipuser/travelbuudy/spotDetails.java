package com.example.sesipuser.travelbuudy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SESIP User on 07-Nov-15.
 */
public class spotDetails extends AppCompatActivity {

    public static final String EXTRA_SPOT = "EXTRA_SPOT";
    private ViewPager mPager;
    private SpotDetailsPageAdapter mPagerAdapter;
    private String mSpot;
//    private PagerTabStrip mTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spotdetails);
        mSpot =getIntent().getStringExtra(EXTRA_SPOT);
        setTitle(mSpot);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SpotDetailsPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

 //       mTabStrip = (PagerTabStrip) findViewById(R.id.tab_strip);
    }

    public class SpotDetailsPageAdapter extends FragmentPagerAdapter {

        public static final int NUM_PAGES = 3;

        public SpotDetailsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return DetailsFragment.newInstance(mSpot);
                case 1:
                    return GalleryFragment.newInstance(mSpot);
                case 2:
                    return ReviewFragment.newInstance(mSpot);
            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Gallery";
                case 2:
                    return "Reviews";
            }

            return "";
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

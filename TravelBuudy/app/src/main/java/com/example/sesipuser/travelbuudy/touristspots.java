package com.example.sesipuser.travelbuudy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.TouristSpotModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by SESIP User on 07-Nov-15.
 */
public class touristspots extends AppCompatActivity {

    public static final String EXTRA_DESTINATION = "EXTRA_DESTINATION";
    private ListView mListView;
    private TouristSpotAdapter mAdapter;
    private String mDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touristspots);

        mDestination = getIntent().getStringExtra(EXTRA_DESTINATION);
        setTitle(mDestination);
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new TouristSpotAdapter(touristspots.this, 0);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String spot = mAdapter.getItem(position).getName();
                Intent intent= new Intent(getApplicationContext(),spotDetails.class);
                intent.putExtra(spotDetails.EXTRA_SPOT,spot);
                startActivity(intent);
                //finish();

            }
        });

        final ApiEndPoints api= ApiBuilder.build();

        api.getTouristSpot(mDestination, new Callback<ArrayList<TouristSpotModel>>() {
            @Override
            public void success(ArrayList<TouristSpotModel> touristSpotModels, Response response) {
                mAdapter.setData(touristSpotModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getApplicationContext(),
                        "Server Down", Toast.LENGTH_LONG).show();
            }
        });


    }

    private class TouristSpotAdapter extends ArrayAdapter<TouristSpotModel> {

        private ArrayList<TouristSpotModel> mData = new ArrayList<>();

        public TouristSpotAdapter(Context context, int resource) {
            super(context, resource);
        }

        public void setData(ArrayList<TouristSpotModel> data) {
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        @Override
        public TouristSpotModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_tourist_spot, parent, false);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);

                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();

            holder.name.setText(getItem(position).getName());

            return convertView;
        }
    }

    public class ViewHolder {
        public TextView name;
    }
}

package com.example.sesipuser.travelbuudy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.HotelModel;
import com.example.sesipuser.travelbuudy.api.responses.TransportModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DESTINATION = "ARG_DESTINATION";

    // TODO: Rename and change types of parameters
    private String mDestination;
    private ListView mListView;
    private HotelFragmentAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mDestination Parameter 1.
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String mDestination) {
        HotelFragment fragment = new HotelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DESTINATION, mDestination);
        fragment.setArguments(args);
        return fragment;
    }

    public HotelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDestination = getArguments().getString(ARG_DESTINATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hotel, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listView);
        mAdapter = new HotelFragmentAdapter(getActivity(), 0);
        mListView.setAdapter(mAdapter);

        final ApiEndPoints api= ApiBuilder.build();

        api.getHotel(mDestination, new Callback<ArrayList<HotelModel>>() {
            @Override
            public void success(ArrayList<HotelModel> hotelModels, Response response) {

                mAdapter.setData(hotelModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getActivity(),
                        "Server Down", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }


    private class HotelFragmentAdapter extends ArrayAdapter<HotelModel> {

        private ArrayList<HotelModel> mData = new ArrayList<>();

        public HotelFragmentAdapter(Context context, int resource) { super(context, resource);}

        public void setData(ArrayList<HotelModel> data) {
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
        public HotelModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_hotel, parent, false);

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

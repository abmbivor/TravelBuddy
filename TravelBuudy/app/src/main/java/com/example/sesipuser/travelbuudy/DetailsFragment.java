package com.example.sesipuser.travelbuudy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.SpotDescriptionModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SPOT = "ARG_SPOT";

    // TODO: Rename and change types of parameters
    private String mSpot;
    private TextView mDescriptionTextView;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param spotname Spotname Text.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String spotname) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SPOT, spotname);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSpot = getArguments().getString(ARG_SPOT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        mDescriptionTextView = (TextView) rootView.findViewById(R.id.description);

        final ApiEndPoints api = ApiBuilder.build();

        api.getsSpotDescription(mSpot, new Callback<SpotDescriptionModel>() {
            @Override
            public void success(SpotDescriptionModel spotDescriptionModel, Response response) {
                String txt = spotDescriptionModel.getDescription();
                mDescriptionTextView.setText(txt);
                //mDescriptionTextView.notify();
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getActivity(),
                        "Server Down", Toast.LENGTH_SHORT).show();

            }
        });
        //mDescriptionTextView.setText(mDescription);

        return rootView;
    }

}

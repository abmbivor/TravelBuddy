package com.example.sesipuser.travelbuudy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.GalleryModel;
import com.example.sesipuser.travelbuudy.api.responses.HotelModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SPOT = "ARG_SPOT";

    // TODO: Rename and change types of parameters
    private String mSpot;
    private ListView mListView;
    private GalleryFragmentAdapter mAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param spotname Parameter 1
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String spotname) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SPOT, spotname);
        fragment.setArguments(args);
        return fragment;
    }

    public GalleryFragment() {
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
        View rootView= inflater.inflate(R.layout.fragment_gallery, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listView);
        mAdapter = new GalleryFragmentAdapter(getActivity(), 0);
        mListView.setAdapter(mAdapter);

        final ApiEndPoints api= ApiBuilder.build();

        api.getImage(mSpot, new Callback<ArrayList<GalleryModel>>() {
            @Override
            public void success(ArrayList<GalleryModel> galleryModels, Response response) {

                mAdapter.setData(galleryModels);
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

    private class GalleryFragmentAdapter extends ArrayAdapter<GalleryModel> {

        private ArrayList<GalleryModel> mData = new ArrayList<>();

        public GalleryFragmentAdapter(Context context, int resource) { super(context, resource);}

        public void setData(ArrayList<GalleryModel> data) {
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
        public GalleryModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_gallery, parent, false);

                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            String url=getItem(position).getUrl();
            Picasso.with(convertView.getContext()).load(url).resize(1024, 680).into(holder.image);
           // holder.name.setText(getItem(position).getUrl());

            return convertView;
        }
    }

    public class ViewHolder {
        public ImageView image;
    }


}

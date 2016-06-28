package com.example.sesipuser.travelbuudy;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
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
import com.example.sesipuser.travelbuudy.api.responses.ReviewModel;
import com.example.sesipuser.travelbuudy.api.responses.TransportModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SPOT = "ARG_SPOT";
    // TODO: Rename and change types of parameters
    private String mSpot;
    private ListView mListView;
    private ReviewFragmentAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param spotname Parameter 1.
     * @return A new instance of fragment ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance(String spotname) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SPOT, spotname);
        fragment.setArguments(args);
        return fragment;
    }

    public ReviewFragment() {
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
        View rootView=inflater.inflate(R.layout.fragment_review, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listView);
        mAdapter = new ReviewFragmentAdapter(getActivity(), 0);
        mListView.setAdapter(mAdapter);

        final ApiEndPoints api= ApiBuilder.build();
        api.getReview(mSpot, new Callback<ArrayList<ReviewModel>>() {
            @Override
            public void success(ArrayList<ReviewModel> reviewModels, Response response) {
                mAdapter.setData(reviewModels);
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


    private class ReviewFragmentAdapter extends ArrayAdapter<ReviewModel> {

        private ArrayList<ReviewModel> mData = new ArrayList<>();

        public ReviewFragmentAdapter(Context context, int resource) { super(context, resource);}

        public void setData(ArrayList<ReviewModel> data) {
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
        public ReviewModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_review, parent, false);

                holder = new ViewHolder();
                holder.review = (TextView) convertView.findViewById(R.id.reviewText);
                holder.username = (TextView) convertView.findViewById(R.id.usernameText);
                holder.datetime = (TextView) convertView.findViewById(R.id.timeText);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();

            holder.review.setText(getItem(position).getReview());
            holder.username.setText(getItem(position).getUsername());
            holder.datetime.setText(getItem(position).getDatetime());

            return convertView;
        }
    }

    public class ViewHolder {
        public TextView review;
        public  TextView username;
        public TextView datetime;
    }



}

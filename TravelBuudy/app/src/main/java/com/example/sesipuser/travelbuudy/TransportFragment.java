package com.example.sesipuser.travelbuudy;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
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
import com.example.sesipuser.travelbuudy.api.responses.TransportModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransportFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SOURCE = "ARG_SOURCE";
    private static final String ARG_DESTINATION = "ARG_DESTINATION";

    // TODO: Rename and change types of parameters
    private String mSource;
    private String mDestination;
    private ListView mListView;
    private TranspotFragmentAdapter mAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param source Parameter 1.
     * @param destination Parameter 2.
     * @return A new instance of fragment TransportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransportFragment newInstance(String source, String destination) {
        TransportFragment fragment = new TransportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SOURCE, source);
        args.putString(ARG_DESTINATION, destination);
        fragment.setArguments(args);
        return fragment;
    }

    public TransportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSource = getArguments().getString(ARG_SOURCE);
            mDestination = getArguments().getString(ARG_DESTINATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_transport, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listView2);
        mAdapter = new TranspotFragmentAdapter(getActivity(), 0);
        mListView.setAdapter(mAdapter);

        final ApiEndPoints api= ApiBuilder.build();
        api.getTransport(mSource, mDestination, new Callback<ArrayList<TransportModel>>() {
            @Override
            public void success(ArrayList<TransportModel> transportModels, Response response) {
                mAdapter.setData(transportModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getActivity(),
                        "Server Down", Toast.LENGTH_SHORT).show();

            }
        });

       /* mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //String spot = mAdapter.getItem(position);
            }
        });  */

        return rootView;
    }

    private class TranspotFragmentAdapter extends ArrayAdapter<TransportModel> {

        private ArrayList<TransportModel> mData = new ArrayList<>();

        public TranspotFragmentAdapter(Context context, int resource) { super(context, resource);}

        public void setData(ArrayList<TransportModel> data) {
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
        public TransportModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_transport, parent, false);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.type = (TextView) convertView.findViewById(R.id.type);

                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();

            holder.name.setText(getItem(position).getName());
            holder.type.setText(getItem(position).getType());

            return convertView;
        }
    }

    public class ViewHolder {
        public TextView name;
        public  TextView type;
    }


}

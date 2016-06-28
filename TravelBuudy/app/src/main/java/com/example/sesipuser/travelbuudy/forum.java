package com.example.sesipuser.travelbuudy;

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
import com.example.sesipuser.travelbuudy.api.responses.ForumModel;
import com.example.sesipuser.travelbuudy.api.responses.ReviewModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by SESIP User on 04-Dec-15.
 */
public class forum extends AppCompatActivity {

    public static final String EXTRA_USERNAME="EXTRA_USERNAME";
    private ListView mListView;
    private ForumAdapter mAdapter;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum2);
        setTitle("Forum Posts");

        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new ForumAdapter(forum.this, 0);
        mListView.setAdapter(mAdapter);
        username = getIntent().getStringExtra(EXTRA_USERNAME);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String thread = mAdapter.getItem(position).getThreadNo();
                String threadUser=mAdapter.getItem(position).getUsername();
                Intent intent = new Intent(getApplicationContext(), threadDetails.class);
                intent.putExtra(threadDetails.EXTRA_THREAD, thread);
                intent.putExtra(threadDetails.EXTRA_USERNAME,username);
                intent.putExtra(threadDetails.EXTRA_THREADUSER,threadUser);
                startActivity(intent);
                //finish();
            }
        });

        final ApiEndPoints api = ApiBuilder.build();

        api.getThreadList(new Callback<ArrayList<ForumModel>>() {
            @Override
            public void success(ArrayList<ForumModel> forumModels, Response response) {

                mAdapter.setData(forumModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getApplicationContext(),
                        "Server Down", Toast.LENGTH_LONG).show();
            }
        });



    }

    private class ForumAdapter extends ArrayAdapter<ForumModel> {

        private ArrayList<ForumModel> mData = new ArrayList<>();

        public ForumAdapter(Context context, int resource) { super(context, resource);}

        public void setData(ArrayList<ForumModel> data) {
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
        public ForumModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_forum2, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.text);
                holder.username = (TextView) convertView.findViewById(R.id.usernameText);
                holder.datetime = (TextView) convertView.findViewById(R.id.timeText);

                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();

            holder.text.setText(getItem(position).getText());
            holder.username.setText(getItem(position).getUsername());
            holder.datetime.setText(getItem(position).getDatetime());

            return convertView;
        }
    }

    public class ViewHolder {
        //public String threadNo;
        public TextView text;
        public  TextView username;
        public TextView datetime;
    }


}

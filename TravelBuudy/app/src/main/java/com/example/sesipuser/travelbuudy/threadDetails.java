package com.example.sesipuser.travelbuudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.ForumModel;
import com.example.sesipuser.travelbuudy.api.responses.ThreadCommentModel;
import com.example.sesipuser.travelbuudy.api.responses.ThreadDetailsModel;
import com.example.sesipuser.travelbuudy.api.responses.ThreadModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by SESIP User on 04-Dec-15.
 */
public class threadDetails extends AppCompatActivity {

    public static final String EXTRA_USERNAME="EXTRA_USERNAME";
    public static final String EXTRA_THREAD="EXTRA_THREAD";
    public static final String EXTRA_THREADUSER="EXTRA_THREADUSER";
    private String threadNo;
    private String username;
    private TextView mText;
    private TextView mThreadUser;
    private ListView mListView;
    private ThreadAdapter mAdapter;
    private String inputText;
    private TextView mThreadTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread1);
        threadNo=getIntent().getStringExtra(EXTRA_THREAD);
        username=getIntent().getStringExtra(EXTRA_USERNAME);
        setTitle("Thread #"+threadNo+ " :");

        mText=(TextView) findViewById(R.id.textView3);
        mThreadUser=(TextView) findViewById(R.id.textView5);
        mThreadTime=(TextView) findViewById(R.id.timeText);

        final ApiEndPoints api= ApiBuilder.build();

        api.getThread(threadNo, new Callback<ThreadModel>() {
            @Override
            public void success(ThreadModel threadModel, Response response) {

                String text = threadModel.getText();
                String threadUser=threadModel.getUsername();
                String threadTime= threadModel.getDatetime();
                mText.setText(text);
                mThreadUser.setText(threadUser);
                mThreadTime.setText(threadTime);
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getApplicationContext(),
                        "Server Down", Toast.LENGTH_LONG).show();
            }
        });

        mListView = (ListView) findViewById(R.id.listView3);
        mAdapter = new ThreadAdapter(threadDetails.this, 0);
        mListView.setAdapter(mAdapter);

        final ApiEndPoints api2=ApiBuilder.build();

        api2.getThreadComments(threadNo, new Callback<ArrayList<ThreadDetailsModel>>() {
            @Override
            public void success(ArrayList<ThreadDetailsModel> threadDetailsModels, Response response) {

                mAdapter.setData(threadDetailsModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getApplicationContext(),
                        "Server Down", Toast.LENGTH_LONG).show();
            }
        });



    }

    private class ThreadAdapter extends ArrayAdapter<ThreadDetailsModel> {

        private ArrayList<ThreadDetailsModel> mData = new ArrayList<>();

        public ThreadAdapter(Context context, int resource) { super(context, resource);}

        public void setData(ArrayList<ThreadDetailsModel> data) {
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
        public ThreadDetailsModel getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_thread1, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.textView6);
                holder.username = (TextView) convertView.findViewById(R.id.textView17);
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

        public TextView text;
        public  TextView username;
        public  TextView datetime;
    }

    public void postReply(View v)
    {
        inputText=((EditText) findViewById(R.id.editText7)).getText().toString();
        if ((inputText.equals("")) || inputText.equals(" "))
        {
            Toast.makeText(getApplicationContext(),
                    "Reply Field Empty ! Try Again", Toast.LENGTH_SHORT).show();
        }
        else
        {
            final ApiEndPoints api=ApiBuilder.build();

            api.postComment(threadNo, inputText, username, new Callback<ThreadCommentModel>() {
                @Override
                public void success(ThreadCommentModel threadCommentModel, Response response) {

                    String s = threadCommentModel.getSuccess();
                    if (s.equals("1")) {

                        Toast.makeText(getApplicationContext(), "Comment Posted Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), forum.class);
                        intent.putExtra(forum.EXTRA_USERNAME,username);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),
                                "Sorry could not post the comment ! Try Again", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void failure(RetrofitError error) {

                    Toast.makeText(getApplicationContext(),
                            "Server Down", Toast.LENGTH_LONG).show();
                }
            });

        }

    }



}

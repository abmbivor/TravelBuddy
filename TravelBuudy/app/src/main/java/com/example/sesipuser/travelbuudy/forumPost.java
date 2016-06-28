package com.example.sesipuser.travelbuudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.PostForumModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by SESIP User on 07-Nov-15.
 */
public class forumPost extends AppCompatActivity {

    public static final String EXTRA_USERNAME="EXTRA_USERNAME";
    private String username;
    private String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum1);
        setTitle("Forum");
        username = getIntent().getStringExtra(EXTRA_USERNAME);
    }
    public void postForum(View v){
        inputText=((EditText) findViewById(R.id.editText)).getText().toString();
        if ((inputText.equals("")) || inputText.equals(" "))
        {
            Toast.makeText(getApplicationContext(),
                    "Field Empty ! Try Again", Toast.LENGTH_SHORT).show();
        }
        else
        {
            final ApiEndPoints api= ApiBuilder.build();
            api.postThread(inputText, username, new Callback<PostForumModel>() {
                @Override
                public void success(PostForumModel postForumModel, Response response) {

                    String s = postForumModel.getSuccess();
                    if (s.equals("1")) {

                        Toast.makeText(getApplicationContext(), "Posted in Forum Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), home.class);
                        intent.putExtra(home.EXTRA_USERNAME,username);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),
                                "Sorry could not post in forum ! Try Again", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void failure(RetrofitError error) {

                    Toast.makeText(getApplicationContext(),
                            "Server Down", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


    public void viewForum(View view){
        Intent intent = new Intent(getApplicationContext(),forum.class);
        intent.putExtra(forum.EXTRA_USERNAME,username);
        startActivity(intent);
    }

}


package com.example.sesipuser.travelbuudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.RegisterModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by SESIP User on 07-Nov-15.
 */
public class registration extends Activity {


    String inputUsername;
    String inputEmail;
    String inputPassword;
    String confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

    }
    public void registrationcomplete(View v){

        inputUsername = ((EditText) findViewById(R.id.editText3)).getText().toString();
        inputEmail = ((EditText) findViewById(R.id.editText4)).getText().toString();
        inputPassword = ((EditText) findViewById(R.id.editText5)).getText().toString();
        confirmPassword = ((EditText) findViewById(R.id.editText6)).getText().toString();

        final ApiEndPoints api = ApiBuilder.build();

        if (  ( !inputUsername.equals("")) && ( !inputPassword.equals("")) && ( !inputEmail.equals("")) &&(inputPassword.equals(confirmPassword)) )
        {
            if ( inputUsername.length() > 4 ){
                api.register(inputUsername,inputEmail,inputPassword,new Callback<RegisterModel>(){
                    public void success(RegisterModel registerModel, Response response) {
                        String s = registerModel.getSuccess();
                        if(s.equals("1")){
                            Toast.makeText(getApplicationContext(),
                                    "Registration Completed Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),login.class));
                            finish();
                        }
                        else if(s.equals("2")){
                            Toast.makeText(getApplicationContext(),
                                    "User Already Exists !!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),
                                    "Server Error Contact Admin", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                       // String h = "";
                    Toast.makeText(getApplicationContext(),
                            "Server Down", Toast.LENGTH_SHORT).show();
                    }

                });

            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "Username should be minimum 5 characters", Toast.LENGTH_SHORT).show();
            }
        }
        else if(!inputPassword.equals(confirmPassword))
        {
            Toast.makeText(getApplicationContext(),
                    "Confirmed password doesn't match with given password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "Fill the fields correctly", Toast.LENGTH_SHORT).show();
        }
    }


}

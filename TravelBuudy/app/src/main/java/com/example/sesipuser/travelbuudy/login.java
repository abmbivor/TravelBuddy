package com.example.sesipuser.travelbuudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sesipuser.travelbuudy.api.ApiBuilder;
import com.example.sesipuser.travelbuudy.api.ApiEndPoints;
import com.example.sesipuser.travelbuudy.api.responses.TestModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Description of LoginClass
 *
 *@author Team TravelBuddy
 * @tag BUET-CSE 11
 *@version 1.01 Build 21 Nov , 2015
 *
 *
*/

public class login extends AppCompatActivity {



    String inputUsername;
    String inputPassword;

    /**
     * Description of onCreate method :
     *
     *      loads the activity_login.xml file when this activity starts
     *
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    /**
     * Description of the registration button onClick response :
     *
     * when the registration button is pressed this method is called and it redirects
     * to another activity whose class file is registration.class
     *
     */

   public void registration(View v){
       startActivity(new Intent(getApplicationContext(), registration.class));
   }


    /**
     * Description of the login button onClick response :
     *
     * This is the most important method of this activity .
     * When the login button is pressed the values of the text fields containing the username and password is checked
     * for validation and then sent to the server as an http post request with the method checkLogin of the class
     * api. The server returns a response of type TestModel as a Callback to the api.checkLogin method. This response
     * contains the validity of the user who tried to log in to the app. All this happens when a successful response is
     * obtained.
     *
     * If the server does not send any successful response then it calls the failure method which will show a
     * Toast message displaying or notifying the user that " The Server is down "
     *
     *
     */

    public void loginClick(View v){
        final ApiEndPoints api = ApiBuilder.build();
        inputUsername = ((EditText) findViewById(R.id.editText)).getText().toString();
        inputPassword = ((EditText) findViewById(R.id.editText2)).getText().toString();
        if (  ( !inputUsername.equals("")) && ( !inputPassword.equals("")) )
        {

            api.checkLogin(inputUsername, inputPassword, new Callback<TestModel>() {
                public void success(TestModel testModel, Response response) {
                    String s = testModel.getSuccess();
                    if(s.equals("true")){
                        Toast.makeText(getApplicationContext(),
                                "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),home.class);
                        intent.putExtra(home.EXTRA_USERNAME,inputUsername);      // keeping the username of the user logged in, in the further activities
                        startActivity(intent);                                  // if login successful redirects to home page
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "Username/Password not Correct!! Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(),
                            "Server Down", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if ( ( !inputUsername.equals("")) )
        {
            Toast.makeText(getApplicationContext(),
                    "Username field empty", Toast.LENGTH_SHORT).show();
        }
        else if ( ( !inputPassword.equals("")) )
        {
            Toast.makeText(getApplicationContext(),
                    "Password field empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "Username and Password field are empty", Toast.LENGTH_SHORT).show();
        }

    }


}

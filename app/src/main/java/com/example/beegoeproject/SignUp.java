package com.example.beegoeproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {

    private EditText emailText,passwordText,repeatPasswordText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Sign up");
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Connect with activity_register.xml
        emailText = findViewById(R.id.emailSignup);
        passwordText = findViewById(R.id.passwordSignup);
        repeatPasswordText = findViewById(R.id.RepeatpasswordSignup);
        signUpButton = findViewById(R.id.signupButton);

        Log.i("wee","Halo");
        //Button Sign up for check email and password
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("wee","Halo");
                if(passwordText.getText().toString().length()>=6&&passwordText.getText().toString().equals(repeatPasswordText.getText().toString())
                        &&passwordText.length()>0&&repeatPasswordText.length()>0&&emailText.length()>0){

                    Toast toast = Toast.makeText ( SignUp.this, "Logged successfully!", Toast.LENGTH_LONG );
                    toast.show ( );


                   PushEmailPassword push = new PushEmailPassword(emailText.getText().toString(),passwordText.getText().toString());
                   push.execute();

                    Intent intent = new Intent(SignUp.this,Display.class);
                    startActivity(intent);

                }
                else if (!(passwordText.getText().toString().equals(repeatPasswordText.getText().toString()))){
                    repeatPasswordText.setError("The password and confirmation password do not match.");

                }
                else {
                    Toast toast = Toast.makeText ( SignUp.this, "Please check your information", Toast.LENGTH_LONG );

                    toast.show ( );
                }

                if(emailText.length()<=0){
                    emailText.setError("Email is invalid");
                }

                if(passwordText.length()<6){
                    passwordText.setError("The Password must be at least 6 characters long");
                }

            }

      });


    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Inner class push email and password to server
    private class PushEmailPassword extends AsyncTask<String, Void, Void> {
        String email, password;
        String urlPush = ("http://lampang.cs.tu.ac.th:3030/register");

        PushEmailPassword(String email, String password) {


            this.email = email;
            this.password = password;

        }

        @Override
        protected Void doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            final JSONObject object = new JSONObject();


            try {

                object.put("email", email);
                object.put("password", password);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(mediaType, object.toString());
            final Request request = new Request.Builder()
                    .url(urlPush)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try {
                Response response = client.newCall(request).execute();

                Log.i("Monday",response.message());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }


}

package com.example.beegoeproject;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class FirsPage extends AppCompatActivity {

    private ImageView signupBtn;
    private TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        getSupportActionBar().hide();

        signupBtn = findViewById(R.id.signupBtn);
        login = findViewById(R.id.login);

        Log.i("Hello","HI EveryONe");

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirsPage.this, SignUp.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirsPage.this, MainActivity.class);
                startActivity(intent);
            }
        });






    }




}

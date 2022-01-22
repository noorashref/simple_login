package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

//    Click the Button to go login page
    public void navigateToLogin(View view) {
        Intent navigate = new Intent();
        navigate.setClass(this,Login.class);
        startActivity(navigate);
    }
}
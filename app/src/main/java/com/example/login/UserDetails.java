package com.example.login;

import static com.example.login.Login.EXTRA_PASSWORD;
import static com.example.login.Login.EXTRA_USERNAME;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {


    private TextView userEmail;
    private TextView userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        
        userEmail = findViewById(R.id.tv_user_email);
        userPassword = findViewById(R.id.tv_user_password);


        Intent getData =  getIntent();
        String str = getData.getStringExtra(EXTRA_USERNAME);
        String pass = getData.getStringExtra(EXTRA_PASSWORD);
        userEmail.setText(str);
        userPassword.setText(pass);
        }

    public void toHome(View view) {
        Intent toHome = new Intent();
        toHome.setClass(this,MainActivity.class);
        startActivity(toHome);
    }
}
    
    

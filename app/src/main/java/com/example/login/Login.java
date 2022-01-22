package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.util.Patterns;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "EXTRA_USERNAME_LoginActivity";
    public static final String EXTRA_PASSWORD = "EXTRA_PASSWORD_LoginActivity";

    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^" +
//                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
//                    "(?=\\S+$)" +            // no white spaces
//                    ".{8,}" +                // at least 4 characters
//                    ".*\\d.*" +              // numbers
//                    "(?=.*[A-Z])"+           // Check Capital letter
//                          // Check small letter
//                    "$");

                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$");

    private EditText userEmail;
    private EditText userPassword;
    private EditText userRepeatPassword;
    private Button saveDetails;
    boolean isEmailValid, isPasswordValid, isRepeatPassword;

    private LinearLayout errorEmail;
    private LinearLayout errorPassword;
    private LinearLayout errorRepeatPassword;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login_error);

        userEmail = findViewById(R.id.et_email);
        userPassword = findViewById(R.id.et_password);
        userRepeatPassword = findViewById(R.id.et_repeat_password);
        saveDetails = findViewById(R.id.btn_next);

//        Setting the linear layout error visibility message for email
        errorEmail = findViewById(R.id.error_email);
        errorEmail.setVisibility(View.GONE);

//        Setting the linear layout error visibility message for password
        errorPassword = findViewById(R.id.error_password);
        errorPassword.setVisibility(View.GONE);

//        Setting the linear layout error visibility message for password
        errorRepeatPassword = findViewById(R.id.password_notmatch);
        errorRepeatPassword.setVisibility(View.GONE);

//        Initialize the background oncreate the page
        userEmail.setBackgroundResource(R.drawable.normal);
        userPassword.setBackgroundResource(R.drawable.normal);
        userRepeatPassword.setBackgroundResource(R.drawable.normal);

//        Click the button and check everything
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                If validation is true send and show the details in userdetail page
                if(setValidation()){
                    showDetails();
                }
                else{
//                    Just to check the condition is true or false
//                    Toast.makeText(Login.this, "Error......", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean setValidation() {

        if (userEmail.getText().toString().isEmpty()) {
//            userEmail.setError("Please enter valid email");

            //Layout visibility
            errorEmail.setVisibility(View.VISIBLE);

            //Background change
            userEmail.setBackgroundResource(R.drawable.shape);
            isEmailValid = false;
        }
//        Checking if the user added @ symbol in the email address
         else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString()).matches()) {
//            userEmail.setError("Please Enter the Valid email id");

            //Background change if error
            userEmail.setBackgroundResource(R.drawable.shape);

            //show the error message
            errorEmail.setVisibility(View.VISIBLE);

            isEmailValid = false;
        } else {
            isEmailValid = true;
            userEmail.setBackgroundResource(R.drawable.green);
            Drawable img = getBaseContext().getResources().getDrawable(R.drawable.tick);
            userEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null ,img,null);
            errorEmail.setVisibility(View.GONE);
        }

        if (userPassword.getText().toString().isEmpty()) {
//            userPassword.setError("No empty password");
            userPassword.setBackgroundResource(R.drawable.shape);
            errorPassword.setVisibility(View.VISIBLE);
            isPasswordValid = false;
        } else if (!PASSWORD_PATTERN.matcher(userPassword.getText().toString()).matches()) {
//            userPassword.setError("Password Too weak");
            userPassword.setBackgroundResource(R.drawable.shape);
            errorPassword.setVisibility(View.VISIBLE);
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
            userPassword.setBackgroundResource(R.drawable.green);
            Drawable img = getBaseContext().getResources().getDrawable(R.drawable.tick);
            userPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null ,img,null);
            errorPassword.setVisibility(View.GONE);
        }
        if(userRepeatPassword.getText().toString().isEmpty()){
            userRepeatPassword.setBackgroundResource(R.drawable.shape);
            userRepeatPassword.setError("Should not empty");
            isRepeatPassword = false;

        }
        else if (!userRepeatPassword.getText().toString().equals(userPassword.getText().toString())) {
//            userRepeatPassword.setError("String not match");
            userRepeatPassword.setBackgroundResource(R.drawable.shape);
            errorRepeatPassword.setVisibility(View.VISIBLE);
            isRepeatPassword = false;
        } else {
//            To check the all validation done
//            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            userRepeatPassword.setBackgroundResource(R.drawable.green);
            Drawable img = getBaseContext().getResources().getDrawable(R.drawable.tick);
            userRepeatPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null ,img,null);
            errorRepeatPassword.setVisibility(View.GONE);
            isRepeatPassword = true;
        }
        if(isEmailValid && isPasswordValid && isRepeatPassword){
            return true;
        }else{
            return false;
        }
    }

    private  void showDetails(){
        String str = userEmail.getText().toString();
        String pass = userPassword.getText().toString();
        Intent toDetails = new Intent();
        toDetails.setClass(this,UserDetails.class);
        toDetails.putExtra(EXTRA_USERNAME,str);
        toDetails.putExtra(EXTRA_PASSWORD,pass);
        startActivity(toDetails);
    }


}
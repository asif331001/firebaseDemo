package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    private EditText signinEmailEditText, signinPasswordEditText;
    private TextView signupHereTextView;
    private Button signinButton;

    private ProgressBar signInProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        this.setTitle("Sign In");

        signinEmailEditText = findViewById(R.id.singinEmailEditTextId);
        signinPasswordEditText = findViewById(R.id.singinPasswordEditTextId);
        signupHereTextView = findViewById(R.id.signupHereId);
        signinButton = findViewById(R.id.signinButtonId);
        signInProgressbar = findViewById(R.id.signinProgressbarId);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        signupHereTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }
}


package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText signUpNameEditText, signUpEmailEditText, signUpPasswordEditText;
    private Button signUpButton;

    private TextView signInHereTextView;

    private ProgressBar signUpProgressbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.setTitle("Sign Up");

        signUpNameEditText = findViewById(R.id.signupNameEditTextId);
        signUpEmailEditText = findViewById(R.id.signupEmailEditTextId);
        signUpPasswordEditText = findViewById(R.id.signupPasswordEditTextId);
        signUpButton = findViewById(R.id.signupPageButtonId);
        signUpProgressbar = findViewById(R.id.signUpProgressbarId);
        signInHereTextView = findViewById(R.id.signInHereId);
        mAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userRegister();
            }
        });

        signInHereTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });

    }

    private void userRegister() {
        String email = signUpEmailEditText.getText().toString().trim();
        String password = signUpPasswordEditText.getText().toString().trim();

        //check email empty or not
        if (email.isEmpty()){
            signUpEmailEditText.setError("Enter an email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        //check email validity
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpEmailEditText.setError("Enter a valid email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        //check password empty or not
        if (password.isEmpty()){
            signUpPasswordEditText.setError("Enter a password");
            signUpPasswordEditText.requestFocus();
            return;
        }

        //check password validity
        if (password.length()<6){
            signUpPasswordEditText.setError("Minimum length of password should be 6");
            signUpPasswordEditText.requestFocus();
            return;
        }

        signUpProgressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signUpProgressbar.setVisibility(View.GONE);
                if (task.isSuccessful()){

                    Toast.makeText(getApplicationContext(),"Sign Up Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

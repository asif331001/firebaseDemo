package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class SignIn extends AppCompatActivity {

    private EditText signinEmailEditText, signinPasswordEditText;
    private TextView signupHereTextView;
    private Button signinButton;

    private ProgressBar signInProgressbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        this.setTitle("Sign In");

        mAuth = FirebaseAuth.getInstance();

        signinEmailEditText = findViewById(R.id.singinEmailEditTextId);
        signinPasswordEditText = findViewById(R.id.singinPasswordEditTextId);
        signupHereTextView = findViewById(R.id.signupHereId);
        signinButton = findViewById(R.id.signinButtonId);
        signInProgressbar = findViewById(R.id.signinProgressbarId);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();

            }
        });

        signupHereTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin() {
        String email = signinEmailEditText.getText().toString().trim();
        String password = signinPasswordEditText.getText().toString().trim();


        if (email.isEmpty()){
            signinEmailEditText.setError("Enter your Email Address");
            signinEmailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signinEmailEditText.setError("Email address is not valid");
            signinEmailEditText.requestFocus();
            return;
        }
        if (password.isEmpty()){
            signinPasswordEditText.setError("Enter your password");
            signinPasswordEditText.requestFocus();
            return;
        }
        if (password.length()<6){
            signinPasswordEditText.setError("Password is too short");
            signinPasswordEditText.requestFocus();
            return;
        }
        signInProgressbar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                signInProgressbar.setVisibility(View.VISIBLE);
                if (task.isSuccessful()){

                    finish();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Email or Password is Wrong", Toast.LENGTH_SHORT).show();
                    signInProgressbar.setVisibility(View.GONE); // hide progress bar on error
                }

            }
        });

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}


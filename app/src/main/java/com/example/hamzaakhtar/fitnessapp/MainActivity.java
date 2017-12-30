package com.example.hamzaakhtar.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;


public class MainActivity extends AppCompatActivity {

    private EditText email, pass;
    private FirebaseAuth FirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.editEmail);
        pass = (EditText) findViewById(R.id.editPass);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInfo()) {
                    String user_Email = email.getText().toString().trim();
                    String user_Pass = pass.getText().toString().trim();

                    FirebaseAuth.signInWithEmailAndPassword(user_Email, user_Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                            }
                            else {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(MainActivity.this, "Login Failed: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        TextView registerTextView = (TextView) findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, register.class));
            }
        });
    }

    private Boolean validateInfo() {
        Boolean valid = false;

        String valEmail = email.getText().toString();
        String valPass = pass.getText().toString();

        if (valEmail.isEmpty() || valPass.isEmpty()) {
            Toast.makeText(this, "Please Fill in all Fields",Toast.LENGTH_SHORT).show();
        }
        else {
            valid = true;
        }

        return valid;
    }
}


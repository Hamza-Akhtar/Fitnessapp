package com.example.hamzaakhtar.fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class register extends AppCompatActivity {

    private EditText username, email, pass, confPass;
    private FirebaseAuth FirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseAuth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.editEmail);
        email = (EditText) findViewById(R.id.editEmail);
        pass = (EditText) findViewById(R.id.editPass);
        confPass = (EditText) findViewById(R.id.editConfPass);


    Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (validateInfo()) {
                String user_Email = email.getText().toString().trim();
                String user_Pass = pass.getText().toString().trim();

                FirebaseAuth.createUserWithEmailAndPassword(user_Email, user_Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(register.this, MainActivity.class));
                        }
                        else {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast.makeText(register.this, "Failed Registration: "+ e.getMessage(), Toast.LENGTH_SHORT).show();                        }
                    }
                });

            }
        }

    });
    }

    private Boolean validateInfo() {
        Boolean valid = false;

        String valUsername = username.getText().toString();
        String valEmail = email.getText().toString();
        String valPass = pass.getText().toString();
        String valConfPass = confPass.getText().toString();

        if (valUsername.isEmpty() || valEmail.isEmpty() || valPass.isEmpty() || valConfPass.isEmpty()) {
            Toast.makeText(this, "Please Fill in all Fields",Toast.LENGTH_SHORT).show();
        }
        else if (!valPass.equals(valConfPass)) {
            Toast.makeText(this, "Please Ensure Passwords Match",Toast.LENGTH_SHORT).show();
        }
        else {
            valid = true;
        }

        return valid;
    }
}

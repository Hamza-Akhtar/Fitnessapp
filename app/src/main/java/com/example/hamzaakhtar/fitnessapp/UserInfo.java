package com.example.hamzaakhtar.fitnessapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class UserInfo extends AppCompatActivity {

    private EditText name, lastName, weight, height, age, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        name = (EditText) findViewById(R.id.editEmail);
        lastName = (EditText) findViewById(R.id.editEmail);
        weight = (EditText) findViewById(R.id.editPass);
        height = (EditText) findViewById(R.id.editConfPass);
        age = (EditText) findViewById(R.id.editEmail);
        gender = (EditText) findViewById(R.id.editEmail);
    }
}

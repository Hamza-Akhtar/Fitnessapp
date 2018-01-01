package com.example.hamzaakhtar.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout);

        Spinner spinnerSplit = (Spinner) findViewById(R.id.spinnerSplit);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CustomWorkout.this, R.layout.spinner_item, getResources().getStringArray(R.array.split));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSplit.setAdapter(myAdapter);


        spinnerSplit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        //Do this and this
                        break;
                    case 1:
                        //Do this and this
                        break;
                    case 2:
                        //Do this and this:
                        break;
                    case 3:
                        //Do this and this:
                        break;
                    case 4:
                        //Do this and this:
                        break;
                    case 5:
                        //Do this and this:
                        break;
                    case 6:
                        //Do this and this:
                        break;
                    default:
                        //For all other cases, do this
                         break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

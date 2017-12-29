package com.example.hamzaakhtar.fitnessapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class WaterCounter extends AppCompatActivity {

    private TextView textCounter, textGoalVal, textIntakeVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_counter);

        textCounter = (TextView) findViewById(R.id.textCounter);
        textGoalVal = (TextView) findViewById(R.id.textGoalVal);
        textIntakeVal = (TextView) findViewById(R.id.textIntakeVal);

        ImageButton btnPlus = (ImageButton) findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int count = Integer.parseInt(textCounter.getText().toString());
                if (count < 100) {
                    count++;
                }
                textCounter.setText(Integer.toString(count));

                String intakeCountStr = textIntakeVal.getText().toString();
                intakeCountStr = intakeCountStr.substring(0, intakeCountStr.length() - 3);
                int intakeCount = Integer.parseInt(intakeCountStr);
                if (intakeCount < 25000)
                intakeCount += 250;
                textIntakeVal.setText(Integer.toString(intakeCount) + " ml");
                }
        });

        ImageButton btnMinus = (ImageButton) findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(textCounter.getText().toString());
                if (count > 0) {
                    count--;
                }
                textCounter.setText(Integer.toString(count));

                String intakeCountStr = textIntakeVal.getText().toString();
                intakeCountStr = intakeCountStr.substring(0, intakeCountStr.length() - 3);
                int intakeCount = Integer.parseInt(intakeCountStr);
                if (intakeCount > 249) {
                    intakeCount -= 250;
                }
                textIntakeVal.setText(Integer.toString(intakeCount) + " ml");
            }
        });
    }
}

package com.example.hamzaakhtar.fitnessapp;


import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class timer extends AppCompatActivity {

    private static long startTimeInMill = 600000;

    private TextView mTextViewCountDown;
    private EditText min;
    private EditText sec;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button setTime;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timer);

        min = (EditText) findViewById(R.id.editMin);
        sec = (EditText) findViewById(R.id.editSec);



        mTextViewCountDown = (TextView) findViewById(R.id.text_view_countdown);

        mButtonStartPause = (Button) findViewById(R.id.button_start_pause);
        mButtonReset = (Button) findViewById(R.id.button_reset);
        setTime = (Button) findViewById(R.id.setTime);


        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateTime()) {
                    startTimeInMill = 000000;
                    mTimeLeftInMillis = startTimeInMill;
                } else {
                    setTimer(min.getText().toString(), sec.getText().toString());
                    if (mTimerRunning)
                        pauseTimer();

                    resetTimer();
                }
                min.setText("");
                sec.setText("");

            }
        });



        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private boolean validateTime () {

        /* Validate Entered time */
        String valMin = min.getText().toString();
        String valSec = sec.getText().toString();

        return !(checkTime(valMin) < 0 || checkTime(valSec) < 0);
    }

    private void setTimer (String min, String sec) {

        long minutes = (long)Integer.parseInt(min) * 60 * 1000;
        long seconds = (long)Integer.parseInt(sec) * 1000;

        startTimeInMill = minutes + seconds;
        mTimeLeftInMillis = startTimeInMill;

    }


    public static int checkTime (String toCheck) {

        try {
            int check = Integer.parseInt(toCheck);
            if (check > 100)
                return -1;
            return 1; 
        }
        catch (NumberFormatException e) {
            return -1;
        }

    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = startTimeInMill;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}





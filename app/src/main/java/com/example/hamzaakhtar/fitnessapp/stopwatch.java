package com.example.hamzaakhtar.fitnessapp;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class stopwatch extends Fragment {


    Button btnStart, btnStop, btnLap;
    TextView txtTimer;
    Handler customHandler = new Handler();
    LinearLayout container;

    long startTime = 0L, timeInMilliseconds = 0L,timeSwapBuff = 0L, updateTime = 0L;

    Runnable updateTimerTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updateTime/1000);
            int mins = secs /60;
            secs %= 60;
            int milliseconds = (int) (updateTime%1000);
            txtTimer.setText(""+mins+":"+String.format("%02d",secs)+":"
                    +String.format("%03d",milliseconds));

            customHandler.postDelayed(this,0);
        }
    };


    public stopwatch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stopwatch, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnStart = (Button) getView().findViewById(R.id.btnStart);
        btnStop = (Button) getView().findViewById(R.id.btnStop);
        btnLap = (Button) getView().findViewById(R.id.btnLap);
        txtTimer = (TextView) getView().findViewById(R.id.timerVal);
        container = (LinearLayout) getView().findViewById(R.id.container);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerTimerThread, 0);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerTimerThread);
            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row,null);
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(txtTimer.getText());
                container.addView(addView);

            }
        });

    }
}

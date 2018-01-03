package com.example.hamzaakhtar.fitnessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class clock extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_timer:
                    setTitle("Timer");
                    startActivity(new Intent(clock.this, timer.class));
                    return true;
                case R.id.navigation_stopwatch:
                    setTitle("Stopwatch");
                    stopwatch fragment2 = new stopwatch();
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.fram,fragment2,"stopwatch");
                    fragmentTransaction2.commit();
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_clock);

              BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
              navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Stopwatch");
        stopwatch fragment2 = new stopwatch();
        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.fram,fragment2,"stopwatch");
        fragmentTransaction2.commit();

    }

}

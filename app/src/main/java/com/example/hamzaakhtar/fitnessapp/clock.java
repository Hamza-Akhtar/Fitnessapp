package com.example.hamzaakhtar.fitnessapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class clock extends AppCompatActivity {

//    private TextView mTextMessage;
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_timer:
//                    setTitle("Timer");
//                    timer fragment = new timer();
//                    android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction1.replace(R.id.fram,fragment,"timer");
//                    fragmentTransaction1.commit();
//                    return true;
//                case R.id.navigation_stopwatch:
//                    setTitle("Stopwatch");
//                    stopwatch fragment2 = new stopwatch();
//                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction2.replace(R.id.fram,fragment2,"stopwatch");
//                    fragmentTransaction2.commit();
//                    return true;
//            }
//            return false;
//        }
//    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_clock);

//        mTextMessage = (TextView) findViewById(R.id.fram);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        setTitle("Timer");
//        timer fragment = new timer();
//        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction1.replace(R.id.fram,fragment,"timer");
//        fragmentTransaction1.commit();

    }

}

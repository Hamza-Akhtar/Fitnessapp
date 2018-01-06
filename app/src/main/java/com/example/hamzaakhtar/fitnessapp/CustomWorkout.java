package com.example.hamzaakhtar.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomWorkout extends AppCompatActivity {

    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout);

        expandableListView = (ExpandableListView) findViewById(R.id.expWorkout);
        final List<String> headings = new ArrayList<String>();
        final HashMap<String, List<String>> child = new HashMap<String, List<String>>();
        final HashMap<String, String> editWorkoutName = new HashMap<String, String>();
        final HashMap<String, String> editSetVal = new HashMap<String, String>();
        final HashMap<String, String> editRepVal = new HashMap<String, String>();

        Spinner spinnerSplit = (Spinner) findViewById(R.id.spinnerSplit);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CustomWorkout.this, R.layout.spinner_item, getResources().getStringArray(R.array.split));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSplit.setAdapter(myAdapter);

        final ListViewAdapter listViewAdapter = new ListViewAdapter(this, headings, child, editWorkoutName, editSetVal, editRepVal,
                new ListViewAdapter.OnEditTextChangeListener() {
                    @Override
                    public void onEditChange(String key, String value) {
                        String pos = key.substring(key.indexOf('-') + 1);
                        key = key.substring(0, key.indexOf('-'));

                        if (pos.equals("0")) {
                            editWorkoutName.put(key, value);
                        }
                        else if (pos.equals("1")) {
                            editSetVal.put(key, value);
                        }
                        else if (pos.equals("2")) {
                            editRepVal.put(key, value);
                        }

                    }
                }); //modified constructor


        expandableListView.setAdapter(listViewAdapter);
        expandableListView.setItemsCanFocus(true);

        spinnerSplit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                headings.clear();
                editWorkoutName.clear();
                editRepVal.clear();
                editSetVal.clear();

                for (int i = 0; i < position + 1; i++) {
                    String add = "Day " + Integer.toString(i + 1);
                    List<String> put = new ArrayList<String>();
                    put.add("Add a Workout");
                    headings.add(add);
                    child.put(add, put);
                }
                listViewAdapter.notifyDataSetChanged();
                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        if (childPosition == listViewAdapter.getChildrenCount(groupPosition) - 1) {
                            
                            String header = "Day " + (groupPosition + 1);
                            child.get(header).add("Add a Workout");
                            listViewAdapter.notifyDataSetChanged();
                            return true;
                        }
                        return false;
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}


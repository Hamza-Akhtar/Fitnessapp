package com.example.hamzaakhtar.fitnessapp;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hamzaakhtar on 2017-12-31.
 */

public class ListViewAdapter extends BaseExpandableListAdapter {

    private List<String> headerTitle;
    private HashMap<String, List<String>> childItems;
    private Context context;
    HashMap<String, String> editTextValues;
    HashMap<String, String> editSetValues;
    HashMap<String, String> editRepValues;

    private OnEditTextChangeListener onEditTextChangeListener;

    public interface OnEditTextChangeListener {
        void onEditChange(String key, String value);
    }

    ListViewAdapter(Context context, List<String> headerTitle, HashMap<String, List<String>> childItems, HashMap<String, String> editTextValues, HashMap<String, String> editSetValues, HashMap<String, String> editRepValues, OnEditTextChangeListener onEditTextChangeListener) {
        this.context = context;
        this.headerTitle = headerTitle;
        this.childItems = childItems;
        this.onEditTextChangeListener = onEditTextChangeListener;
        this.editTextValues = editTextValues;
        this.editSetValues = editSetValues;
        this.editRepValues = editRepValues;
    }


    @Override
    public int getGroupCount() {
        return headerTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childItems.get(headerTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childItems.get(headerTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String) this.getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_view_header, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.headerParent);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        int count = getChildrenCount(groupPosition);

        final String title = (String) this.getChild(groupPosition, childPosition);
        final String pos =  Integer.toString(groupPosition) + Integer.toString(childPosition);

        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {
            if (!isLastChild) {
                convertView = layoutInflater.inflate(R.layout.add_workout, null);
                EditText editText = (EditText)convertView.findViewById(R.id.editWrkName);
                for (Map.Entry<String, String> entry : editTextValues.entrySet()) { //loop to set the values that you saved
                    if (entry.getKey().equals(pos)) {
                        editText.setText(entry.getValue());
                    }
                }
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String newPos = pos + "-0";
                        onEditTextChangeListener.onEditChange(newPos, editable.toString());
                    }
                });

                EditText editTextSet = (EditText)convertView.findViewById(R.id.editSet);
                for (Map.Entry<String, String> entry : editSetValues.entrySet()) { //loop to set the values that you saved
                    if (entry.getKey().equals(pos)) {
                        editTextSet.setText(entry.getValue());
                    }
                }
                editTextSet.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String newPos = pos + "-1";
                        onEditTextChangeListener.onEditChange(newPos, editable.toString());
                    }
                });

                EditText editTextRep = (EditText)convertView.findViewById(R.id.editRep);
                for (Map.Entry<String, String> entry : editRepValues.entrySet()) { //loop to set the values that you saved
                    if (entry.getKey().equals(pos)) {
                        editTextRep.setText(entry.getValue());
                    }
                }
                editTextRep.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String newPos = pos + "-2";
                        onEditTextChangeListener.onEditChange(newPos, editable.toString());
                    }
                });

            } else if (isLastChild) {
                convertView = layoutInflater.inflate(R.layout.list_view_child, null);
                TextView textView = (TextView) convertView.findViewById(R.id.headerChild);
                textView.setText(title);
            }
        } else {
            if (isLastChild) {
                convertView = layoutInflater.inflate(R.layout.list_view_child, null);
                TextView textView = (TextView) convertView.findViewById(R.id.headerChild);
                textView.setText(title);
            } else {
                convertView = layoutInflater.inflate(R.layout.add_workout, null);
                EditText editText = (EditText) convertView.findViewById(R.id.editWrkName);
                for (Map.Entry<String, String> entry : editTextValues.entrySet()) { //loop to set the values that you saved
                    if (entry.getKey().equals(pos)) {
                        editText.setText(entry.getValue());
                    }
                }
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String newPos = pos + "-0";
                        onEditTextChangeListener.onEditChange(newPos, editable.toString());
                    }
                });

                EditText editTextSet = (EditText)convertView.findViewById(R.id.editSet);
                for (Map.Entry<String, String> entry : editSetValues.entrySet()) { //loop to set the values that you saved
                    if (entry.getKey().equals(pos)) {
                        editTextSet.setText(entry.getValue());
                    }
                }
                editTextSet.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String newPos = pos + "-1";
                        onEditTextChangeListener.onEditChange(newPos, editable.toString());
                    }
                });

                EditText editTextRep = (EditText)convertView.findViewById(R.id.editRep);
                for (Map.Entry<String, String> entry : editRepValues.entrySet()) { //loop to set the values that you saved
                    if (entry.getKey().equals(pos)) {
                        editTextRep.setText(entry.getValue());
                    }
                }
                editTextRep.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String newPos = pos + "-2";
                        onEditTextChangeListener.onEditChange(newPos, editable.toString());
                    }
                });
            }
        }

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

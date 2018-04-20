package com.example.irmin.test1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Event_Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__register);
    }

    public void Move(View view) {
        TimePickerFragment mTimePickerFragment = new TimePickerFragment();
        mTimePickerFragment.show(getFragmentManager(), "TimePicker");
    }
}

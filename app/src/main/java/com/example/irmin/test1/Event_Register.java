package com.example.irmin.test1;


import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class Event_Register extends AppCompatActivity {

    TextView tv1, tv2;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__register);
        tv1 = findViewById(R.id.TimeView_S);
        tv2 = findViewById(R.id.TimeView_E);
    }

    public void Move_Start(View view) {
        TimePickerFragment mTimePickerFragment = new TimePickerFragment();
        mTimePickerFragment.show(getFragmentManager(), "TimePicker");
    }

    public void Move_End(View view) {
        TimePickerFragmentEnd mTimePickerFragmentEnd = new TimePickerFragmentEnd();
        mTimePickerFragmentEnd.show(getFragmentManager(), "TimePicker");
    }
}

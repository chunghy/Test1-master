package com.example.irmin.test1;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimePickerFragmentEnd extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    Calendar mCalendar = Calendar.getInstance();
    int hour, min ;

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        // Set a variable to hold the current time AM PM Status
        // Initially we set the variable value to AM
        String status = "AM";

        if(hourOfDay > 11)
        {
            // If the hour is greater than or equal to 12
            // Then the current AM PM status is PM
            status = "PM";
        }

        // Initialize a new variable to hold 12 hour format hour value
        int hour_of_12_hour_format;

        if(hourOfDay > 11){

            // If the hour is greater than or equal to 12
            // Then we subtract 12 from the hour to make it 12 hour format time
            hour_of_12_hour_format = hourOfDay - 12;
        }
        else {
            hour_of_12_hour_format = hourOfDay;
        }
        // Get the calling activity TextView reference
        TextView tv2 = (TextView) getActivity().findViewById(R.id.close_Time);
        // Display the 12 hour format time in app interface
        tv2.setText(hour_of_12_hour_format + " :" + minute + " " + status);

        Toast.makeText(getActivity(), hourOfDay + "시" + minute+ "분 " + "으로 설정되었습니다", Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        min = mCalendar.get(Calendar.MINUTE);

        TimePickerDialog mTimePickerDialog = new TimePickerDialog(
                getContext(), this,  hour, min, android.text.format.DateFormat.is24HourFormat(getContext()));
        return mTimePickerDialog;
    }

}
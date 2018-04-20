package com.example.irmin.test1;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int min = mCalendar.get(Calendar.MINUTE);

        TimePickerDialog mTimePickerDialog = new TimePickerDialog(
                getContext(), this, hour, min, android.text.format.DateFormat.is24HourFormat(getContext()));
        return mTimePickerDialog;
    }
}

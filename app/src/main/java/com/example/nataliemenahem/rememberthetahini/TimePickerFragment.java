package com.example.nataliemenahem.rememberthetahini;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by NatalieMenahem on 01/01/2016.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        System.out.println("****************************************");
        System.out.println(hourOfDay + ":" + minute);
        System.out.println("****************************************");

        // Return input text to activity
        Intent data = new Intent();
        //data.putExtra("timePicker", timePicker);
        data.putExtra("hour", hourOfDay);
        data.putExtra("minute", minute);
        DialogListener activity = (DialogListener) getActivity();
        activity.onFinishEditDialog(data);
        this.dismiss();
    }
}

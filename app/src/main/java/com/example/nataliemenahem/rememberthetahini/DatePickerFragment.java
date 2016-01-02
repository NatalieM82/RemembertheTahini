package com.example.nataliemenahem.rememberthetahini;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by NatalieMenahem on 01/01/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("Step #2");
        final Calendar c = Calendar.getInstance();
        boolean editMode = false;


        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
//        return new DatePickerDialog(getActivity(), this, year, month, day);

        DatePickerDialog d = new DatePickerDialog(getActivity(), this, year, month, day);
        DatePicker dp = d.getDatePicker();
        dp.setMinDate(c.getTimeInMillis());
        return d;
    }

    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Because problem with month in DatePicker we fix it with +1 but not in edit mode!!
        month++;

        // Return input text to activity
        Intent data = new Intent();
        //---set the data to pass back---
        data.putExtra("year", year);
        data.putExtra("month", month);
        data.putExtra("day", day);
        DialogListener activity = (DialogListener) getActivity();
        activity.onFinishEditDialog(data);
        //---closes the activity---
        this.dismiss();
    }
}

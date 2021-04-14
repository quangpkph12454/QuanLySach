package com.example.duanmau;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by amin on 6/18/18
 */

public class DatePickerFragment extends DialogFragment {
    int year,month,day;
    public DatePickerFragment() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }
}

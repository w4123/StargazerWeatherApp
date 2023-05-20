package com.stargazerweatherapp.data.models;

import android.os.Build;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateGetter {
    public static List<LocalDate> getDates(int NumberOfDates){
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < NumberOfDates; ++i) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                dates.add(LocalDate.now().plusDays(i));
            }
        }
        return dates;
    }
    public static List<LocalDate> getDates() {
        return getDates(365);
    }
}

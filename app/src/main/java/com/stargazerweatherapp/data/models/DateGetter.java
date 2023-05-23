package com.stargazerweatherapp.data.models;

import android.os.Build;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateGetter {//Functional Class to get a list of all the days the next n days, defaulting to 365
    public static List<LocalDate> getDates(int NumberOfDates){
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < NumberOfDates; ++i) {
            dates.add(LocalDate.now().plusDays(i));
        }
        return dates;
    }
    public static List<LocalDate> getDates() {
        return getDates(365);
    }
}

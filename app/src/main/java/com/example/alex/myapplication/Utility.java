package com.example.alex.myapplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utility {

    public static String getCurrentDate() {
        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String day = dateFormatter.format(now);
        return day;
    }

}

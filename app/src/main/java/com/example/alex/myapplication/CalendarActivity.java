package com.example.alex.myapplication;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
String newDay;
Calendar calendar;
int dayOfMonth1;
    MainActivity mainActivity;
   static DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 mainActivity = new MainActivity();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



CalendarView calendarView = findViewById(R.id.calendarView);
Date now = new Date();
Calendar calendar1 = new GregorianCalendar(2018,Calendar.MARCH,1);


        calendarView.setMinDate(calendar1.getTimeInMillis());
        calendarView.setMaxDate(now.getTime());

calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

         calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date date = calendar.getTime();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
dayOfMonth1= dayOfMonth;

        newDay = dateFormatter.format(date);
       DailyDbHelper dailyDbHelper = new DailyDbHelper(getApplicationContext(), null, null, 1);

        Food food = dailyDbHelper.loadTodaysFoodTotalNutrientsHandler(newDay);

        Bundle bundle = new Bundle();
        int daof =dayOfMonth1 ;
        bundle.putInt("message", daof );
        TodayFoodFragment tff = new TodayFoodFragment();
        tff.setArguments(bundle);
        getFragmentManager()
                .beginTransaction();



         tff.test(newDay);

        // MainActivityFragment.update(newDay);

    }
});




    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;


    }


}

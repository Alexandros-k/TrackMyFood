package com.example.alex.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TodayFood extends AppCompatActivity {

    DailyDbHelper dailyDbHelper;
    Button deleteBtn;
    DataProviderToCustomAdapter dp;
    CustomAdapter2 customAdapter;
    ListView lv;
    ArrayList<Food> foodList;
    int i=0;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_food);

        lv =  findViewById(R.id.listview1);
        if(customAdapter==null) {
            customAdapter = new CustomAdapter2(getApplicationContext());
            lv.setAdapter(customAdapter);
        }

        dailyDbHelper = new DailyDbHelper(TodayFood.this, null, null, 1);
        final String day = dailyDbHelper.day;
        TextView tv1 = findViewById(R.id.textView9);
        tv1.setText(day);
        displayTotalMicroNutrients(dailyDbHelper.loadHandler5(day));

        Button previousBtn = findViewById(R.id.previousBtn);

            previousBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    i++;
                    yesterday(i);
                    if(dailyDbHelper.tableExists(getYesterdayDateString())) {
                        showYesterdayFoods(getYesterdayDateString());
                    }else{i--;}
                }
            });

        Button nextBtn = findViewById(R.id.nextDayBtn);
            nextBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    i--;
                    if(dailyDbHelper.tableExists(getYesterdayDateString())) {
                        showNextDayFoods(getNextDateString(i));
                    }else{i++;}
                }
            });
    }





    public void displayTotalMicroNutrients(Food food) {
        TextView tv = findViewById(R.id.textView10);

        tv.setText("                         Today You have consumed: \n"+
                "kcal " + food.getGram()+", " + " Gram " + food.getKcal()+" gr, "
                + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr, ");
    }



    private Calendar yesterday(int i) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        return cal;
    }



    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("E_d_M_y");
        return dateFormat.format(yesterday(i).getTime());
    }



    public void showYesterdayFoods(String date) {

        dailyDbHelper = new DailyDbHelper(this, null, null, 1);
         foodList = dailyDbHelper.loadYesterdayHandler2(date);

             lv = findViewById(R.id.listview1);

             customAdapter = new CustomAdapter2(getApplicationContext(), foodList);
             lv.setAdapter(customAdapter);

             TextView tv1 = findViewById(R.id.textView9);
             tv1.setText(getYesterdayDateString());
             displayTotalMicroNutrients(dailyDbHelper.loadHandler5(date));
         }




    private String getNextDateString(int i) {
         cal = Calendar.getInstance();
                  cal.add(Calendar.DATE, -i);
        DateFormat dateFormat = new SimpleDateFormat("E_d_M_y");
        return dateFormat.format(cal.getTime());

    }


    public void showNextDayFoods(String nextDay) {

        dailyDbHelper = new DailyDbHelper(this, null, null, 1);
        final ArrayList<Food> foodList = dailyDbHelper.loadNextDayHandler(nextDay);
        lv = (ListView) findViewById(R.id.listview1);

        customAdapter = new CustomAdapter2(getApplicationContext(),foodList);
        lv.setAdapter(customAdapter);

        TextView tv1 = findViewById(R.id.textView9);
        tv1.setText(getNextDateString(i));
        displayTotalMicroNutrients(dailyDbHelper.loadHandler5(nextDay));


    }


}






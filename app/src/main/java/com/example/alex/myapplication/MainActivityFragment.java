package com.example.alex.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex on 2/14/2018.
 */

public class MainActivityFragment extends Fragment {
    DailyDbHelper dailyDbHelper;
    private static final String TAG="MainActivityFragment";
    TextView tv1;
    TextView tv;
   static TextView tv2;
    Food food;


    public MainActivityFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        tv1 =view.findViewById(R.id.textView12);
        tv = view.findViewById(R.id.textView11);
        tv2= view.findViewById(R.id.textView2);
        displayMicroNutrients();
        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        String day = dateFormatter.format(now);

        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        day = dailyDbHelper.day;
        food = dailyDbHelper.loadHandler5(day);

        return view;
    }






    public void displayMicroNutrients() {

        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        String day = dateFormatter.format(now);

        tv1.setText(day);
        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        day = dailyDbHelper.day;
        food = dailyDbHelper.loadHandler5(day);

        tv.setText("Today You have consumed: ");
        tv2.setText("kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr ");


    }

    public static void newdisplayMicroNutrients(Food food) {



        tv2.setText("kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr ");


    }

   public void update(){
       newdisplayMicroNutrients(food);



   }



}



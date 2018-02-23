package com.example.alex.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
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
    ArrayList<Food> list;
    private static final String TAG="MainActivityFragment";
     String day;
    TextView tv1;
    TextView tv;
    TextView tv2;
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

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayMicroNutrients();

    }

    public void displayMicroNutrients() {

        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        String day = dateFormatter.format(now);
        tv1 =getView().findViewById(R.id.textView12);
        tv = getView().findViewById(R.id.textView11);
        tv1.setText(day);
        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        day = dailyDbHelper.day;
        food = dailyDbHelper.loadHandler5(day);
tv2= getView().findViewById(R.id.textView2);
        tv.setText("Today You have consumed: ");
    tv2.setText("kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr ");


    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         tv1 =getView().findViewById(R.id.textView12);
        tv1.setText(day);



         tv = getView().findViewById(R.id.textView11);

        tv.setText("                         Today You have consumed: \n"+
                "kcal " + food.getGram()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr, ");




    }*/
}
    /*Button AddFoodBtn= getView().findViewById(R.id.buttonAddFood);
        AddFoodBtn.setVisibility(View.INVISIBLE);


                Button ShowFoodBtn=getView().findViewById(R.id.ShowFoodId);
                ShowFoodBtn.setVisibility(View.INVISIBLE);


                Button dbFoodsBtn= getView().findViewById(R.id.buttondb);
                dbFoodsBtn.setVisibility(View.INVISIBLE);*/
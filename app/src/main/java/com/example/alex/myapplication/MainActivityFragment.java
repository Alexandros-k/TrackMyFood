package com.example.alex.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex on 2/14/2018.
 */

public class MainActivityFragment extends Fragment implements TodayFoodFragment.Listener{
    DailyDbHelper dailyDbHelper;
    private static final String TAG="MainActivityFragment";
    static TextView tv1;
    TextView tv;
    static TextView tv2;
  static  Food food;
    String day;
   static  BarChart barChart;
   static  ArrayList<BarEntry> barEntries;
   static  ArrayList<String> xAXis;

    public MainActivityFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        tv1 =view.findViewById(R.id.textView12);
        tv = view.findViewById(R.id.textView11);
        tv2= view.findViewById(R.id.textView2);
        barChart = view.findViewById(R.id.barChart);
        displayMicroNutrients();
        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        day = dateFormatter.format(now);

        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        day = dailyDbHelper.day;
        food = dailyDbHelper.loadHandler5(day);




        tv1.setText(day);


    }

    @Override
    public void onResume() {
        super.onResume();
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
        String day1 = tv1.getText().toString();

       dis(day1);

    }



    public void displayMicroNutrients() {

   /*     Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        String day = dateFormatter.format(now);
*/
        tv1.setText(day);
        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        day = dailyDbHelper.day;
        food = dailyDbHelper.loadHandler5(day);

        barEntries= new ArrayList<>();
        barEntries.add(new BarEntry(((float) food.getKcal()),0));
        barEntries.add(new BarEntry(((float) food.getProtein()),1));
        barEntries.add(new BarEntry(((float) food.getCarbs()),2));
        barEntries.add(new BarEntry(((float) food.getFats()),3));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Data");
        int[] colors = new int[] {Color.GREEN, Color.YELLOW, Color.BLACK, Color.BLUE};
        barDataSet.setColors(colors);
        barDataSet.setBarSpacePercent(50f);
        barChart.getAxisLeft().setDrawGridLines(false);
        //  barChart.getAxisLeft().setEnabled(false);

        barChart.getAxisRight().setTextColor(R.color.colorText);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getLegend().setEnabled(false);
        barChart.setDescription("Gram");
        barChart.getAxisLeft().setAxisMinValue(0f);// prepei na ta perasw san dekadikous gia na to bgalw
        barChart.animateXY(2000, 2000);
        barChart.invalidate();

        xAXis = new ArrayList<>();
        xAXis.add("Kcal");
        xAXis.add("Protein");
        xAXis.add("Carbs");
        xAXis.add("Fats");

        BarData theData = new BarData(xAXis,barDataSet);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);



        tv.setText("Today You have consumed: ");
        tv2.setText("kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr ");


    }

    public static void newdisplayMicroNutrients(Food food1) {

        food=food1;
        barEntries= new ArrayList<>();
        barEntries.add(new BarEntry(((float) food.getKcal()),0));
        barEntries.add(new BarEntry(((float) food.getProtein()),1));
        barEntries.add(new BarEntry(((float) food.getCarbs()),2));
        barEntries.add(new BarEntry(((float) food.getFats()),3));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Data");
        int[] colors = new int[] {Color.GREEN, Color.YELLOW, Color.BLACK, Color.BLUE};
        barDataSet.setColors(colors);
        barDataSet.setBarSpacePercent(50f);
        barChart.getAxisLeft().setDrawGridLines(false);
        //  barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setTextColor(R.color.colorText);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getLegend().setEnabled(false);
        barChart.setDescription("Gram");
        barChart.getAxisLeft().setAxisMinValue(0f);// prepei na ta perasw san dekadikous gia na to bgalw
        barChart.animateXY(2000, 2000);
        barChart.invalidate();

        xAXis = new ArrayList<>();
        xAXis.add("Kcal");
        xAXis.add("Protein");
        xAXis.add("Carbs");
        xAXis.add("Fats");

        BarData theData = new BarData(xAXis,barDataSet);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        tv2.setText("kcal " + food1.getKcal()+", " + " protein " + food1.getProtein()+" gr, carbs " + food1.getCarbs()+" gr, " + " fats " + food1.getFats()+" gr ");


    }

    public static  void update(String day){
      tv1.setText(day);
   }

    public void dis(String day ){


    //dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);

    food = dailyDbHelper.loadHandler5(day);

    barEntries= new ArrayList<>();
    barEntries.add(new BarEntry(((float) food.getKcal()),0));
    barEntries.add(new BarEntry(((float) food.getProtein()),1));
    barEntries.add(new BarEntry(((float) food.getCarbs()),2));
    barEntries.add(new BarEntry(((float) food.getFats()),3));
    BarDataSet barDataSet = new BarDataSet(barEntries,"Data");
    int[] colors = new int[] {Color.GREEN, Color.YELLOW, Color.BLACK, Color.BLUE};
    barDataSet.setColors(colors);
    barDataSet.setBarSpacePercent(50f);
    barChart.getAxisLeft().setDrawGridLines(false);
  //  barChart.getAxisLeft().setEnabled(false);
    barChart.getAxisRight().setTextColor(R.color.colorText);
    barChart.getAxisRight().setDrawGridLines(false);
    barChart.getAxisRight().setEnabled(false);
    barChart.getXAxis().setDrawGridLines(false);
    barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
    barChart.getLegend().setEnabled(false);
    barChart.setDescription("Gram");
    barChart.getAxisLeft().setAxisMinValue(0f);// prepei na ta perasw san dekadikous gia na to bgalw
    barChart.animateXY(2000, 2000);
    barChart.invalidate();

    xAXis = new ArrayList<>();
    xAXis.add("Kcal");
    xAXis.add("Protein");
    xAXis.add("Carbs");
    xAXis.add("Fats");

    BarData theData = new BarData(xAXis,barDataSet);
    barChart.setData(theData);
    barChart.setTouchEnabled(true);
    barChart.setDragEnabled(true);
    barChart.setScaleEnabled(true);



    //tv2.setText("kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr ");


}


    public void updateTest(String day) {
        tv1.setText(day);
    }
}



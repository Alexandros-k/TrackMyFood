package com.example.alex.myapplication.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.alex.myapplication.Database.DatabaseHandler;
import com.example.alex.myapplication.Models.Food;
import com.example.alex.myapplication.R;
import com.example.alex.myapplication.Utility;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;

/**
 * Created by Alex on 2/14/2018.
 */

public class MainActivityFragment extends Fragment implements TodayFoodFragment.Listener {
    private DatabaseHandler DbHelper;
    private static final String TAG = "MainActivityFragment";
    private static TextView tv1;
    private TextView tv;
    private static TextView tv2;
    private static Food food;
    private String day;
    private static BarChart barChart;
    private static ArrayList<BarEntry> barEntries;
    private static ArrayList<String> xAXis;
    private int textColor;
    private BarDataSet barDataSet;
    private BarData barChartData;
    private int[] colors;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        textColor = getResources().getColor(R.color.colorText);
        tv1 = view.findViewById(R.id.textView12);
        tv = view.findViewById(R.id.textView11);
        tv2 = view.findViewById(R.id.textView2);
        barChart = view.findViewById(R.id.barChart);
        day = Utility.getCurrentDate();
        DbHelper = new DatabaseHandler(getActivity(), null, null, 1);
        food = DbHelper.loadTodaysFoodTotalNutrientsHandler(day);
        displayMicroNutrients(food);
        tv1.setText(day);
        tv.setText("Today You have consumed: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
        showBarChart(food);
    }

    public void displayMicroNutrients(Food food) {
        showBarChart(food);
        tv2.setText("kcal " + food.getKcal() + ", " + " protein " + food.getProtein() + " gr, carbs " + food.getCarbs() + " gr, " + " fats " + food.getFats() + " gr ");
    }

    public static void update(String day) {
        tv1.setText(day);
    }

    public void showBarChart(Food food) {
        createBarChartData(food);
        setupBarChart();
        barChart.setData(barChartData);
    }

    private void createBarChartData(Food food) {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(((float) food.getKcal()), 0));
        barEntries.add(new BarEntry(((float) food.getProtein()), 1));
        barEntries.add(new BarEntry(((float) food.getCarbs()), 2));
        barEntries.add(new BarEntry(((float) food.getFats()), 3));
        barDataSet = new BarDataSet(barEntries, "Data");
        colors = new int[]{Color.GREEN, Color.YELLOW, Color.BLACK, Color.BLUE};
        xAXis = new ArrayList<>();
        xAXis.add("Kcal");
        xAXis.add("Protein");
        xAXis.add("Carbs");
        xAXis.add("Fats");
        barDataSet.setColors(colors);
        barDataSet.setBarSpacePercent(50f);
        barChartData = new BarData(xAXis, barDataSet);
        barChartData.getDataSetByIndex(0).setValueTextColor(-11098468);
        barChartData.getDataSetByIndex(0).setValueTextSize(10);
    }

    private void setupBarChart() {
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setDescriptionColor(-11098468);
        barChart.setDescription("(Gram)");
        barChart.setDescriptionPosition(900, 500);
        barChart.getXAxis().setTextColor(-11098468);
        barChart.getAxisLeft().setTextColor(-11098468);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisLeft().setAxisMinValue(0f);// prepei na ta perasw san dekadikous gia na to bgalw
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);
    }

    public void updateTest(String day) {
        tv1.setText(day);
    }
}



package com.example.alex.myapplication.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.alex.myapplication.Database.DailyDbHelper;
import com.example.alex.myapplication.Models.Food;
import com.example.alex.myapplication.Models.DataProviderToCustomAdapter;
import com.example.alex.myapplication.R;
import com.example.alex.myapplication.Fragments.TodayFoodFragment;
import com.example.alex.myapplication.Utility;

import java.util.ArrayList;


/**
 * Created by Alex on 2/10/2018.
 */

public class TodayFoodCustomAdapter extends BaseAdapter {
    ArrayList<Food> foodList;
    ArrayList<DataProviderToCustomAdapter> dpList;
    DailyDbHelper dailyDbHelper;
    DataProviderToCustomAdapter dp;
    TodayFoodFragment todayFood;
    String day = Utility.getCurrentDate();

    public TodayFoodCustomAdapter(Context context, TodayFoodFragment tf) {
        todayFood = tf;
        dailyDbHelper = new DailyDbHelper(context, null, null, 1);
        foodList = dailyDbHelper.loadFoodHandler(day);
        dpList = new ArrayList<>();
        for (int i = 0; i < foodList.size(); i++) {
            dp = new DataProviderToCustomAdapter(
                    foodList.get(i).getID(),
                    foodList.get(i).getName(),
                    foodList.get(i).getGram(),
                    foodList.get(i).getKcal(),
                    foodList.get(i).getProtein(),
                    foodList.get(i).getCarbs(),
                    foodList.get(i).getFats()
            );
            dpList.add(dp);
        }
    }

    public TodayFoodCustomAdapter(Context context, ArrayList<Food> foodList1, TodayFoodFragment tf) {
        todayFood = tf;
        dailyDbHelper = new DailyDbHelper(context, null, null, 1);
        foodList = foodList1;
        dpList = new ArrayList<>();
        for (int i = 0; i < foodList.size(); i++) {
            dp = new DataProviderToCustomAdapter(
                    foodList.get(i).getID(),
                    foodList.get(i).getName(),
                    foodList.get(i).getGram(),
                    foodList.get(i).getKcal(),
                    foodList.get(i).getProtein(),
                    foodList.get(i).getCarbs(),
                    foodList.get(i).getFats()

            );
            dpList.add(dp);
        }
    }

    @Override
    public int getCount() {
        return dpList.size();
        // total number of elements in the list
    }

    @Override
    public Object getItem(int position) {
        return dpList.get(position);    // single item in the list
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.custom_adapter_layout, parent, false);
        }

        TextView foodId = view.findViewById(R.id.tvId);
        TextView foodName = view.findViewById(R.id.tvName);
        TextView foodKcal = view.findViewById(R.id.tvKcal);
        TextView foodGram = view.findViewById(R.id.tvGram);
        TextView foodProtein = view.findViewById(R.id.tvProtein);
        TextView foodCarbs = view.findViewById(R.id.tvCarbs);
        TextView foodFats = view.findViewById(R.id.tvFats);

        foodId.setText("ID: " + String.valueOf(dpList.get(position).getID()));
        foodName.setText(String.valueOf(dpList.get(position).getName()));
        foodKcal.setText(String.valueOf(dpList.get(position).getKcal()) + " gr");
        foodGram.setText(String.valueOf(dpList.get(position).getGram()));
        foodProtein.setText(String.valueOf(dpList.get(position).getProtein() + " gr"));
        foodCarbs.setText(String.valueOf(dpList.get(position).getCarbs()) + " gr");
        foodFats.setText(String.valueOf(dpList.get(position).getFats()) + " gr");
        Button deleteBtn = view.findViewById(R.id.buttondel);

        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               final ArrayList<Food> foodList = dailyDbHelper.loadFoodHandler(todayFood.geRequestedDate(todayFood.counter));
                int i = foodList.get(position).getID();
                dailyDbHelper.deleteFoodHandler(i);
                dpList.remove(position);
                todayFood.displayTotalMicroNutrients();
                notifyDataSetChanged();
            }
        });
        return view;
    }
}

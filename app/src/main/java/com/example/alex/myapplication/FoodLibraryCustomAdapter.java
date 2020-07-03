package com.example.alex.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


/**
 * Created by Alex on 2/10/2018.
 */

public class FoodLibraryCustomAdapter extends BaseAdapter {
    ArrayList<Food> foodList;
    ArrayList<DataProviderToCustomAdapter> dpList;
    DatabaseHandler dbHandler;
    DataProviderToCustomAdapter dp;
    DailyDbHelper dailyDbHelper;

    public FoodLibraryCustomAdapter(Context context) {
        dbHandler = new DatabaseHandler(context, null, null, 1);
        foodList = dbHandler.loadFoodsHandler();
        dpList = new ArrayList<>();
        dailyDbHelper = new DailyDbHelper(context, null, null, 1);

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
        return dpList.size(); // total number of elements in the list
    }

    @Override
    public Object getItem(int position) {
        return dpList.get(position);  // single item in the list
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

        foodId.setText(String.valueOf(dpList.get(position).getID()));
        foodName.setText(String.valueOf(dpList.get(position).getName()));
        foodKcal.setText(String.valueOf(dpList.get(position).getKcal()));
        foodGram.setText(String.valueOf(dpList.get(position).getGram()));
        foodProtein.setText(String.valueOf(dpList.get(position).getProtein()));
        foodCarbs.setText(String.valueOf(dpList.get(position).getCarbs()));
        foodFats.setText(String.valueOf(dpList.get(position).getFats()));

        Button deleteBtn = view.findViewById(R.id.buttondel);
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final ArrayList<Food> foodList = dbHandler.loadFoodsHandler();
                int i = foodList.get(position).getID();
                dbHandler.deleteFoodHandler(i);
                dpList.remove(position);
                notifyDataSetChanged();
            }
        });

        Button addBtn = view.findViewById(R.id.buttonAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), PopUp.class);
                in.putExtra("position", position);
                startActivity(v.getContext(), in, null);
            }
        });
        return view;
    }
}

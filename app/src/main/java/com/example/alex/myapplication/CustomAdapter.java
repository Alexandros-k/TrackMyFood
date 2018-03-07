package com.example.alex.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.example.alex.myapplication.MyDBHandler.TABLE_NAME;

/**
 * Created by Alex on 2/6/2018.
 */

public class CustomAdapter extends ArrayAdapter  {
    DataProviderToCustomAdapter dp;
    DailyDbHelper  dailyDbHelper ;
ListView iv;
int position;
View view;
     ListView lv;
    static class DataHandler{

        TextView foodName;
        TextView foodKcal;
        TextView foodGram;
        TextView foodProtein;
        TextView foodCarbs;
        TextView foodFats;
        TextView foodId;

    }

    List list=new ArrayList();

    public CustomAdapter(Context context, int resource) {

        super(context, resource);

    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }


    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        view = convertView;

        DataHandler handler;
        Button button;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_adapter_layout, parent, false);

        } else {
            handler = (DataHandler) view.getTag();
        }
        handler = new DataHandler();
        handler.foodId = view.findViewById(R.id.tvId);
        handler.foodName = view.findViewById(R.id.tvName);
        handler.foodKcal = view.findViewById(R.id.tvKcal);
        handler.foodGram = view.findViewById(R.id.tvGram);
        handler.foodProtein = view.findViewById(R.id.tvProtein);
        handler.foodCarbs = view.findViewById(R.id.tvCarbs);
        handler.foodFats = view.findViewById(R.id.tvFats);


        DataProviderToCustomAdapter dataProvider;
        dataProvider = (DataProviderToCustomAdapter) this.getItem(position);
        handler.foodId.setText(String.valueOf(dataProvider.getID()));
        handler.foodName.setText(dataProvider.getName());
        handler.foodKcal.setText(String.valueOf(dataProvider.getKcal()));
        handler.foodGram.setText(String.valueOf(dataProvider.getGram()));
        handler.foodProtein.setText(String.valueOf(dataProvider.getProtein()));
        handler.foodCarbs.setText(String.valueOf(dataProvider.getCarbs()));
        handler.foodFats.setText(String.valueOf(dataProvider.getFats()));

            delete(position, view);

            return view;


    }


    public void delete(final int position, View view){
        Button deleteBtn = view.findViewById(R.id.buttondel);
         lv = view. findViewById(R.id.listview1);


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dailyDbHelper = new DailyDbHelper(getContext(), null, null, 1);
                final ArrayList<Food> foodList = dailyDbHelper.loadHandler2();
                int i = foodList.get(position).getID();
                int g = foodList.indexOf(foodList.get(position));
                dailyDbHelper.deleteHandler3(i);
            }

        });}



}

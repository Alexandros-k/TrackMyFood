package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AllFood extends AppCompatActivity {
MyDBHandler dbHandler;
    DataProviderToCustomAdapter dp;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_food);
        ListView lv = (ListView) findViewById(R.id.lv2);
        CustomAdapter3 customAdapter = new CustomAdapter3(getApplicationContext());
        lv.setAdapter(customAdapter);



       // showCustomTodayFoods();

    }

    public void showCustomTodayFoods() {

        dbHandler = new MyDBHandler(this, null, null, 1);
        final ArrayList<Food> foodList = dbHandler.loadHandler2();
        ListView lv = (ListView) findViewById(R.id.lv2);

        final CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_adapter_layout);
        lv.setAdapter(customAdapter);

        for (int i = 0; i < foodList.size(); i++)

        {

            dp = new DataProviderToCustomAdapter(
                    foodList.get(i).getID(),
                    foodList.get(i).getName(),
                    foodList.get(i).getKcal(),
                    foodList.get(i).getGram(),
                    foodList.get(i).getProtein(),
                    foodList.get(i).getCarbs(),
                    foodList.get(i).getFats()
            );
            customAdapter.add(dp);


            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapter, View v, int position,
                                               long id) {

                    int i = foodList.get(position).getID();

                    dbHandler.deleteHandler3(i);


                    return false;
                }
            });

        }


    }
}

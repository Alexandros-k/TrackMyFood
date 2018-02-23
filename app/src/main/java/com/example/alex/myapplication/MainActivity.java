package com.example.alex.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
DailyDbHelper dailyDbHelper;
    ArrayList<Food> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }


}
/*

    Button AddFoodBtn=  findViewById(R.id.buttonAddFood);
        AddFoodBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, AddFood.class));
        }
    });


    Button ShowFoodBtn= findViewById(R.id.ShowFoodId);
        ShowFoodBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dailyDbHelper = new DailyDbHelper(getApplicationContext(), null, null, 1);



            if(dailyDbHelper.tableExists(dailyDbHelper.day)) {
                startActivity(new Intent(MainActivity.this, TodayFood.class));

            }else {
                Toast.makeText(MainActivity.this, "You havent eat anything today. Please add food",
                        Toast.LENGTH_LONG).show();
                dailyDbHelper.makeTable(dailyDbHelper.day,dailyDbHelper);
            }

        }
    });


    Button dbFoodsBtn= (Button) findViewById(R.id.buttondb);
        dbFoodsBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, AllFood.class));


        }
    });

*/
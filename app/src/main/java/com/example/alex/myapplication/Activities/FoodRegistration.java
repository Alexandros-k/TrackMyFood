package com.example.alex.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alex.myapplication.R;

import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;

import com.example.alex.myapplication.Models.Food;
import com.example.alex.myapplication.Database.DatabaseHandler;

public class FoodRegistration extends AppCompatActivity {
    EditText foodName;
    EditText foodKcal;
    EditText foodGram;
    EditText foodProtein;
    EditText foodCarbs;
    EditText foodFats;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_registration);
        foodName = findViewById(R.id.foodNameId);
        foodKcal = findViewById(R.id.KcalId);
        foodGram = findViewById(R.id.gramId);
        foodProtein = findViewById(R.id.proteinId);
        foodCarbs = findViewById(R.id.carbsId);
        foodFats = findViewById(R.id.fatsId);
        textView = findViewById(R.id.textView8);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Button SaveFoodBtn = findViewById(R.id.SaveFoodId);
        SaveFoodBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        addFood(view);
                        Toast.makeText(FoodRegistration.this, "Food saved!",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }


                });
    }

    public void addFood(View view) {
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        String name = foodName.getText().toString();
        int kcal = Integer.parseInt(foodKcal.getText().toString());
        int gram = Integer.parseInt(foodGram.getText().toString());
        int protein = Integer.parseInt(foodProtein.getText().toString());
        int carbs = Integer.parseInt(foodCarbs.getText().toString());
        int fats = Integer.parseInt(foodFats.getText().toString());
        Food food = new Food(name, kcal, gram, protein, carbs, fats);
        dbHandler.addFoodHandler(food);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

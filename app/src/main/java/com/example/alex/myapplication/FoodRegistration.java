package com.example.alex.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodRegistration extends AppCompatActivity {
    EditText foodName ;
    EditText foodKcal ;
    EditText foodGram ;
    EditText foodProtein ;
    EditText foodCarbs;
    EditText foodFats ;
    TextView textView;
    String name;


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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             name = extras.getString("Name");
            int gram =extras.getInt("Gram");

            foodName.setText(name);
            foodGram.setText(String.valueOf(gram));
        }




        Button SaveFoodBtn = (Button) findViewById(R.id.SaveFoodId);


        SaveFoodBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        addFood(view);
                        addFoodToday(name);
                        Toast.makeText(FoodRegistration.this, "Food saved!",
                                Toast.LENGTH_LONG).show();
                        startActivity(new Intent(FoodRegistration.this, TodayFood.class));
                         }


                });









    }

    private void addFoodToday(String name) {

        DailyDbHelper dailyDbHelper = new DailyDbHelper(this, null, null, 1);
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        if (dailyDbHelper == null) {

            Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y");
        String day = dateFormatter.format(now);
            dailyDbHelper.makeTable(day, dailyDbHelper);
        }

         Food f1 = dbHandler.findHandler(name);

            dailyDbHelper.addHandler(f1);

    }

    public void addFood(View view) {
       MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        //int id = Integer.parseInt(foodid.getText().toString());
        String name = foodName.getText().toString();
        int kcal = Integer.parseInt(foodKcal.getText().toString());
        int gram = Integer.parseInt(foodGram.getText().toString());
        int protein = Integer.parseInt(foodProtein.getText().toString());
        int carbs = Integer.parseInt(foodCarbs.getText().toString());
        int fats = Integer.parseInt(foodFats.getText().toString());
        Food food = new Food( name,kcal,gram,protein,carbs,fats);
        dbHandler.addHandler(food);

    }

    public void loadFoods(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        textView.setText(dbHandler.loadHandler());
        foodName.setText("");
        foodKcal.setText("");
        foodGram.setText("");
        foodProtein.setText("");
        foodCarbs.setText("");
        foodFats.setText("");

    }






}

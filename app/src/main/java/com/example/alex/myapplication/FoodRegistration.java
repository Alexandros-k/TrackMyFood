package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodRegistration extends AppCompatActivity {
    EditText foodName;
    EditText foodKcal;
    EditText foodGram;
    EditText foodProtein;
    EditText foodCarbs;
    EditText foodFats;
    TextView textView;
    String name;
    ViewPager mViewPager;
    Button mButton;

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
      /*  Bundle extras = getIntent().getExtras();
        if (extras != null) {
             name = extras.getString("Name");
            int gram =extras.getInt("Gram");

            foodName.setText(name);
            foodGram.setText(String.valueOf(gram));
        }*/
        Button SaveFoodBtn = findViewById(R.id.SaveFoodId);
        SaveFoodBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        addFood(view);
                        // addFoodToday(name);
                        Toast.makeText(FoodRegistration.this, "Food saved!",
                                Toast.LENGTH_LONG).show();
                        finish();

                    }


                });
    }

    private void addFoodToday(String name) {
        DailyDbHelper dailyDbHelper = new DailyDbHelper(this, null, null, 1);
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        if (dailyDbHelper == null) {
            Date now = new Date();
            DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y");
            String day = dateFormatter.format(now);
           // dailyDbHelper.makeTable(day, dailyDbHelper);
        }
        Food f1 = dbHandler.findFoodHandler(name);
        dailyDbHelper.addTodayFoodHandler(f1);
    }

    public void addFood(View view) {
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        //int id = Integer.parseInt(foodid.getText().toString());
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

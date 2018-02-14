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

public class AddFood extends AppCompatActivity {


    EditText foodName ;
    EditText foodGram;
    String name;
    int gram;
    Date now;
    MyDBHandler dbHandler;
    DailyDbHelper dailyDbHelper;
    SimpleDateFormat dateFormatter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);


        foodName = findViewById(R.id.addFoodNameId);
        foodGram = findViewById(R.id.addGramFoodId);

        //

        Button mButton1 = (Button) findViewById(R.id.addFoodId);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = foodName.getText().toString();
                gram = Integer.parseInt(foodGram.getText().toString());
                addFood(name,gram);
            }
        });

       /* Button mButton2 = (Button) findViewById(R.id.button);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddFood.this, FoodRegistration.class));
            }
        });*/










    }

    public void addFood(String name,int gram){

        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y");
        String day = dateFormatter.format(now);



        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        DailyDbHelper dailyDbHelper = new DailyDbHelper(this, null, null, 1);

        Food f1 = dbHandler.findHandler(name);

      if(f1==null){

          Intent intent =new Intent(this, FoodRegistration.class);
          intent.putExtra("Name",name);
          intent.putExtra("Gram",gram);

          startActivity(intent);



      }else {
          Food foodCopy = dbHandler.findHandler(f1.getName());
            dailyDbHelper.makeTable(day,dailyDbHelper);


          dailyDbHelper.addHandler(foodCopy);

          Toast.makeText(AddFood.this, "Food saved!",
                  Toast.LENGTH_LONG).show();
          startActivity(new Intent(AddFood.this, MainActivity.class));
      }




    }




    }










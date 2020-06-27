package com.example.alex.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DailyGoalActivity extends AppCompatActivity {
    TextView targetCalories ;
    TextView tarcalories ;
    TextView proteinGr;
    TextView carbGr ;
    TextView fatsGr ;
    Button button;
    //int targetCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


         targetCalories = (TextView)findViewById(R.id.calid);
         tarcalories = (TextView)findViewById(R.id.textView24);
         proteinGr = (TextView)findViewById(R.id.textView25);
         carbGr = (TextView)findViewById(R.id.textView26);
         fatsGr = (TextView)findViewById(R.id.textView27);
         button= (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        createMicroNutrients();

              }
        });

        }



    public void createMicroNutrients() {

        int targetCal = Integer.parseInt(targetCalories.getText().toString());





        int protgr = (targetCal*30/100)/4;
      int carbgr = (targetCal*50/100)/4;
      int fatsgr = (targetCal*20/100)/9;


      DailyGoal dg = new DailyGoal(targetCal,protgr,carbgr,fatsgr);
        int ds = dg.getKcal();

        tarcalories.setText("Target Calories :"+(String.valueOf(dg.getKcal())));
        proteinGr.setText("Target protein :"+(String.valueOf(dg.getProtein())));
        carbGr.setText("Target carbs :"+(String.valueOf(dg.getCarbs())));
        fatsGr.setText("Target fats :"+(String.valueOf(dg.getFats())));

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}

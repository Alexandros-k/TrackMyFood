package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex on 2/14/2018.
 */

public class TodayFoodFragment extends Fragment {

    private static final String TAG="TodayFoodFragment";

    DailyDbHelper dailyDbHelper;
    CustomAdapter2 customAdapter;
    ListView lv;
    ArrayList<Food> foodList;
    int i=0;
    Calendar cal;
    TextView tv1;
    Main2Activity main2Activity;
    TextView tv;
    Food food;
    DateFormat dateFormat;
    TextView tev;
    Button previousBtn;
    Button nextBtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);

    }




    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TodayFoodFragment todayFoodFragment = new TodayFoodFragment();
        todayFoodFragment.setArguments(bundle);
        return todayFoodFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_today_food,container,false);
        lv = view.findViewById(R.id.listview1);
        tv1 = view.findViewById(R.id.textView9);
        tv = view.findViewById(R.id.textView10);
        tev = view.findViewById(R.id.buttondel);
        previousBtn =view.findViewById(R.id.previousBtn);
        nextBtn =view.findViewById(R.id.nextDayBtn);
        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        main2Activity = new Main2Activity();
        dateFormat = new SimpleDateFormat("E_d_M_y",Locale.ENGLISH);
        customAdapter = new CustomAdapter2(getContext(),this);
        tv1.setText(main2Activity.day);
        lv.setAdapter(customAdapter);
        displayTotalMicroNutrients(main2Activity.day);



        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                appearButtons(v);
                return false;
            }
        });


        previousBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i++;
                yesterday(i);
                if(dailyDbHelper.tableExists(getYesterdayDateString())) {
                    showYesterdayFoods(getYesterdayDateString());
                }else{i--;}
            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i--;
                if(dailyDbHelper.tableExists(getYesterdayDateString())) {
                    showNextDayFoods(getNextDateString(i));
                }else{i++;}
            }
        });

        return view;
    }




    @Override
    public void setMenuVisibility(final boolean visible) {
        if (visible) {
            tv1.getText().toString();
            displayTotalMicroNutrients(tv1.getText().toString());
         }
        super.setMenuVisibility(visible);
    }

    @Override
    public void onResume() {
        super.onResume();
       // dailyDbHelper = new DailyDbHelper(getContext(), null, null, 1);
        ArrayList<Food> foodlist= dailyDbHelper.loadHandler2(tv1.getText().toString());
        customAdapter = new CustomAdapter2(getContext(),foodlist,this);
        lv.setAdapter(customAdapter);
        displayTotalMicroNutrients(tv1.getText().toString());

    }

    private void appearButtons(View v) {
        tev = v.findViewById(R.id.buttondel);
        if(tev.getVisibility()==View.GONE) {
            tev.setVisibility(View.VISIBLE);
        }else if(tev.getVisibility()==View.VISIBLE) {
            tev.setVisibility(View.GONE);
        }
    }

    public void displayTotalMicroNutrients() {
        //dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        food = dailyDbHelper.loadHandler5(dailyDbHelper.day);

        tv.setText("               Today You have consumed: \n"+
                "kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr, ");
        MainActivityFragment.newdisplayMicroNutrients(food);
    }

    public void displayTotalMicroNutrients(String day) {
        //dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        food = dailyDbHelper.loadHandler5(day);

        tv.setText("               Today You have consumed: \n"+
                "kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr, ");
    }

    private Calendar yesterday(int i) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        return cal;
    }

    private String getYesterdayDateString() {
       // DateFormat dateFormat = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        return dateFormat.format(yesterday(i).getTime());
    }

    public void showYesterdayFoods(String date) {
        //dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        foodList = dailyDbHelper.loadYesterdayHandler2(date);
        customAdapter = new CustomAdapter2(getActivity(), foodList);
        lv.setAdapter(customAdapter);
        tv1.setText(getYesterdayDateString());
        displayTotalMicroNutrients(date);
    }

    private String getNextDateString(int i) {
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
      //  dateFormat = new SimpleDateFormat("E_d_M_y",Locale.ENGLISH);
        return dateFormat.format(cal.getTime());

    }

    public void showNextDayFoods(String nextDay) {

        //dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        foodList = dailyDbHelper.loadNextDayHandler(nextDay);
        customAdapter = new CustomAdapter2(getActivity(),foodList);
        lv.setAdapter(customAdapter);
        tv1.setText(getNextDateString(i));
        displayTotalMicroNutrients(nextDay);
    }



}

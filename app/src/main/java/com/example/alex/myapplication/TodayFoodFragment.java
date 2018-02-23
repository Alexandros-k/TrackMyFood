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
    String day1;
    boolean BtnIsPressed;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public TodayFoodFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_today_food,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv =  getView().findViewById(R.id.listview1);

        tv1 =getView().findViewById(R.id.textView9);
        main2Activity = new Main2Activity();
        tv1.setText(main2Activity.day);

            customAdapter = new CustomAdapter2(getActivity());
            lv.setAdapter(customAdapter);




         dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
         displayTotalMicroNutrients(dailyDbHelper.loadHandler5(dailyDbHelper.day));
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {

                TextView tev = v.findViewById(R.id.buttondel);
                if(tev.getVisibility()==View.INVISIBLE) {
                    tev.setVisibility(View.VISIBLE);
                }else if(tev.getVisibility()==View.VISIBLE) {
                    tev.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        Button previousBtn =getView().findViewById(R.id.previousBtn);
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

        Button nextBtn =getView().findViewById(R.id.nextDayBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i--;
                if(dailyDbHelper.tableExists(getYesterdayDateString())) {
                    showNextDayFoods(getNextDateString(i));
                }else{i++;}
            }
        });


    }


    public void displayTotalMicroNutrients(Food food) {
         tv =getView().findViewById(R.id.textView10);

        tv.setText("               Today You have consumed: \n"+
                "kcal " + food.getKcal()+", " + " protein " + food.getProtein()+" gr, carbs " + food.getCarbs()+" gr, " + " fats " + food.getFats()+" gr, ");
    }

    private Calendar yesterday(int i) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        return cal;
    }

    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        return dateFormat.format(yesterday(i).getTime());
    }

    public void showYesterdayFoods(String date) {

        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        foodList = dailyDbHelper.loadYesterdayHandler2(date);

        lv =getView().findViewById(R.id.listview1);

        customAdapter = new CustomAdapter2(getActivity(), foodList);
        lv.setAdapter(customAdapter);

        TextView tv1 =getView().findViewById(R.id.textView9);
        tv1.setText(getYesterdayDateString());
        displayTotalMicroNutrients(dailyDbHelper.loadHandler5(date));
    }

    private String getNextDateString(int i) {
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        DateFormat dateFormat = new SimpleDateFormat("E_d_M_y",Locale.ENGLISH
        );
        return dateFormat.format(cal.getTime());

    }

    public void showNextDayFoods(String nextDay) {

        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        final ArrayList<Food> foodList = dailyDbHelper.loadNextDayHandler(nextDay);
        lv = getView().findViewById(R.id.listview1);

        customAdapter = new CustomAdapter2(getActivity(),foodList);
        lv.setAdapter(customAdapter);

        TextView tv1 = getView().findViewById(R.id.textView9);
        tv1.setText(getNextDateString(i));
        displayTotalMicroNutrients(dailyDbHelper.loadHandler5(nextDay));


    }
}

package com.example.alex.myapplication;

import android.content.Context;
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
    private static final String TAG = "TodayFoodFragment";
    public DailyDbHelper dailyDbHelper;
    public CustomAdapter2 customAdapter;
    public ListView lv;
    public ArrayList<Food> foodList;
    public int counter = 0;
    public Calendar cal;
    public static TextView tv1;
    public MainActivity mainActivity;
    public TextView tv;
    public Food food;
    public DateFormat dateFormat;
    public TextView tev;
    public Button previousBtn;
    public Button nextBtn;
    public Listener mListener;
    public String day;
    public Date now;
    public DateFormat dateFormatter;
    public String startingDate;
    private String formattedStartingDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface Listener {
        void updateTest(String day);
    }

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TodayFoodFragment todayFoodFragment = new TodayFoodFragment();
        todayFoodFragment.setArguments(bundle);
        return todayFoodFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_food, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final View view = getView();
        lv = view.findViewById(R.id.listview1);
        tv1 = view.findViewById(R.id.textView9);
        tv = view.findViewById(R.id.textView10);
        tev = view.findViewById(R.id.buttondel);
        previousBtn = view.findViewById(R.id.previousBtn);
        nextBtn = view.findViewById(R.id.nextDayBtn);
        nextBtn.setVisibility(View.INVISIBLE);
        dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        startingDate = dailyDbHelper.getFirstRecord();
        mainActivity = new MainActivity();
        customAdapter = new CustomAdapter2(getContext(), this);
        now = new Date();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        day = dateFormatter.format(now);
        tv1.setText(day);
        lv.setAdapter(customAdapter);
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
                if (!startingDate.equals(getYesterdayDateString())) {
                    counter++;
                    yesterday(counter);
                    nextBtn.setVisibility(View.VISIBLE);
                    showYesterdayFoods(getYesterdayDateString());
                    MainActivityFragment.update(getYesterdayDateString());
                    if (startingDate.equals(getYesterdayDateString())) {
                        previousBtn.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getNextDateString(counter - 1).equals(day)) {
                    nextBtn.setVisibility(View.INVISIBLE);
                    counter--;
                    showNextDayFoods(getNextDateString(counter));
                    displayTotalMicroNutrients(getNextDateString(counter));
                } else if (!getNextDateString(counter).equals(day)) {
                    previousBtn.setVisibility(View.VISIBLE);
                    counter--;
                    showNextDayFoods(getNextDateString(counter));
                    displayTotalMicroNutrients(getNextDateString(counter));
                }
            }
        });
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
        ArrayList<Food> foodlist = dailyDbHelper.loadTodaysFoodMainPanelHandler(tv1.getText().toString());
        customAdapter = new CustomAdapter2(getContext(), foodlist, this);
        lv.setAdapter(customAdapter);
        displayTotalMicroNutrients(tv1.getText().toString());
    }

    private void appearButtons(View v) {
        tev = v.findViewById(R.id.buttondel);
        if (tev.getVisibility() == View.GONE) {
            tev.setVisibility(View.VISIBLE);
        } else if (tev.getVisibility() == View.VISIBLE) {
            tev.setVisibility(View.GONE);
        }
    }

    public void displayTotalMicroNutrients() {
        //dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        food = dailyDbHelper.loadTodaysFoodTotalNutrientsHandler(dailyDbHelper.day);
        tv.setText("               Today You have consumed: \n" +
                "kcal " + food.getKcal() + ", " + " protein " + food.getProtein() + " gr, carbs " + food.getCarbs() + " gr, " + " fats " + food.getFats() + " gr, ");
        MainActivityFragment.newdisplayMicroNutrients(food);
    }

    public void displayTotalMicroNutrients(String day) {
        MainActivityFragment.update(day);
        //mListener.updateTest(day);
        food = dailyDbHelper.loadTodaysFoodTotalNutrientsHandler(day);
        tv.setText("               Today You have consumed: \n" +
                "kcal " + food.getKcal() + ", " + " protein " + food.getProtein() + " gr, carbs " + food.getCarbs() + " gr, " + " fats " + food.getFats() + " gr, ");
        MainActivityFragment.newdisplayMicroNutrients(food);
    }

    private Calendar yesterday(int i) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        return cal;
    }

    private String getYesterdayDateString() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormatter.format(yesterday(counter).getTime());
    }

    public void showYesterdayFoods(String date) {
        //dailyDbHelper = new DailyDbHelper(getActivity(), null, null, 1);
        foodList = dailyDbHelper.loadYesterdaysFoodHandler(date);
        customAdapter = new CustomAdapter2(getActivity(), foodList);
        lv.setAdapter(customAdapter);
        tv1.setText(getYesterdayDateString());
        displayTotalMicroNutrients(date);
    }

    private String getNextDateString(int i) {
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormatter.format(cal.getTime());
    }

    public void showNextDayFoods(String nextDay) {
        foodList = dailyDbHelper.loadNextDaysFoodHandler(nextDay);
        customAdapter = new CustomAdapter2(getActivity(), foodList);
        lv.setAdapter(customAdapter);
        tv1.setText(getNextDateString(counter));
        displayTotalMicroNutrients(nextDay);
    }

    public void test(String day) {
        tv1.setText(day);
    }
}

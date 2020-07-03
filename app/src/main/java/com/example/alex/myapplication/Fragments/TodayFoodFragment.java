package com.example.alex.myapplication.Fragments;

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
import com.example.alex.myapplication.CustomAdapters.TodayFoodCustomAdapter;
import com.example.alex.myapplication.Database.DatabaseHandler;
import com.example.alex.myapplication.Models.Food;
import com.example.alex.myapplication.MainActivity;
import com.example.alex.myapplication.R;
import com.example.alex.myapplication.Utility;
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
    public DatabaseHandler DbHelper;
    public TodayFoodCustomAdapter todayFoodCustomAdapter;
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
    public MainActivityFragment maf;

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
         maf = new MainActivityFragment();
        final View view = getView();
        lv = view.findViewById(R.id.listview1);
        tv1 = view.findViewById(R.id.textView9);
        tv = view.findViewById(R.id.textView10);
        tev = view.findViewById(R.id.buttondel);
        previousBtn = view.findViewById(R.id.previousBtn);
        nextBtn = view.findViewById(R.id.nextDayBtn);
        nextBtn.setVisibility(View.INVISIBLE);
        DbHelper = new DatabaseHandler(getActivity(), null, null, 1);
        startingDate = DbHelper.getFirstRecord();
        if(startingDate == null){
            previousBtn.setVisibility(View.INVISIBLE);
        }else{previousBtn.setVisibility(View.VISIBLE);}
        mainActivity = new MainActivity();
        todayFoodCustomAdapter = new TodayFoodCustomAdapter(getContext(), this);
        day = Utility.getCurrentDate();
        tv1.setText(day);
        lv.setAdapter(todayFoodCustomAdapter);
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

                    if (!startingDate.equals(geRequestedDate(counter))) {
                        counter++;
                        nextBtn.setVisibility(View.VISIBLE);
                        showRequestedDayFoods(geRequestedDate(counter));
                        MainActivityFragment.update(geRequestedDate(counter));
                        if (startingDate.equals(geRequestedDate(counter))) {
                            previousBtn.setVisibility(View.INVISIBLE);
                        }
                    }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!geRequestedDate(counter).equals(day)) {
                    previousBtn.setVisibility(View.VISIBLE);
                    counter--;
                    showRequestedDayFoods(geRequestedDate(counter));
                    displayTotalMicroNutrients();
                    if (geRequestedDate(counter).equals(day)) {
                        nextBtn.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        if (visible) {
            tv1.getText().toString();
            displayTotalMicroNutrients();
        }
        super.setMenuVisibility(visible);
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Food> foodlist = DbHelper.loadFoodHandler(tv1.getText().toString());
        todayFoodCustomAdapter = new TodayFoodCustomAdapter(getContext(), foodlist, this);
        lv.setAdapter(todayFoodCustomAdapter);
        displayTotalMicroNutrients();
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
        String day = geRequestedDate(counter);
        MainActivityFragment.update(day);
        food = DbHelper.loadTodaysFoodTotalNutrientsHandler(day);
        tv.setText("               Today You have consumed: \n" +
                "kcal " + food.getKcal() + ", " + " protein " + food.getProtein() + " gr, carbs " + food.getCarbs() + " gr, " + " fats " + food.getFats() + " gr, ");
        maf.displayMicroNutrients(food);
    }
//this is used to get either previous or next date
    public String geRequestedDate(int i) {
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -i);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormatter.format(cal.getTime());
    }

    public void showRequestedDayFoods(String RequestedDay) {
        foodList = DbHelper.loadFoodHandler(RequestedDay);
        todayFoodCustomAdapter = new TodayFoodCustomAdapter(getActivity(), foodList,this);
        lv.setAdapter(todayFoodCustomAdapter);
        tv1.setText(RequestedDay);
        displayTotalMicroNutrients();
    }

    public void test(String day) {
        tv1.setText(day);
    }
}

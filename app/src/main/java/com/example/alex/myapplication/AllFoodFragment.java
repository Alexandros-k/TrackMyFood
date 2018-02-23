package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Alex on 2/14/2018.
 */

public class AllFoodFragment extends Fragment {

    private static final String TAG = "AllFoodFragment";

    DailyDbHelper dailyDbHelper;
    Button deleteBtn;
    DataProviderToCustomAdapter dp;
    CustomAdapter3 customAdapter;
    ListView lv;
    ArrayList<Food> foodList;
    int i = 0;
    Calendar cal;
    TextView tev;
    TextView tev2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }


    public AllFoodFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_all_food, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         lv = getView().findViewById(R.id.lv2);
        customAdapter = new CustomAdapter3(getContext());
        lv.setAdapter(customAdapter);



        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                ConstraintLayout das = getView().findViewById(R.id.custAdaptId);
                ViewGroup.LayoutParams dir = das.getLayoutParams();



                tev = v.findViewById(R.id.buttondel);
                 tev2 = v.findViewById(R.id.buttonAdd);
               if(tev.getVisibility()==View.INVISIBLE && tev2.getVisibility()==View.INVISIBLE) {
                   tev.setVisibility(View.VISIBLE);
                   tev2.setVisibility(View.VISIBLE);
                   //dir.height= Integer.parseInt("90");

               }else if(tev.getVisibility()==View.VISIBLE && tev2.getVisibility()==View.VISIBLE) {
                   tev.setVisibility(View.INVISIBLE);
                   tev2.setVisibility(View.INVISIBLE);
                   //dir.height= Integer.parseInt("30");
               }
                return false;
            }
        });





    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);






    }
}

package com.example.alex.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by Alex on 2/14/2018.
 */

public class AllFoodFragment extends Fragment  {

    private static final String TAG = "AllFoodFragment";

    DailyDbHelper dailyDbHelper;
    CustomAdapter3 customAdapter;
    ListView lv;
    int i = 0;
   static TextView tv;
    static TextView  tv2;
    FloatingActionButton myFab;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_food,container,false);


        return view;
    }



    @Override
    public void onStart() {
        super.onStart();

        View view = getView();

        lv = view.findViewById(R.id.lv2);
        customAdapter = new CustomAdapter3(getContext());
        lv.setAdapter(customAdapter);

        tv2 = view.findViewById(R.id.buttonAdd);

        myFab =view.findViewById(R.id.fabId);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FoodRegistration.class));
            }
        });




        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,int index, long arg3) {
                showButtons(v);
                return false;
            }
        });

    }


    public void showButtons(View view) {

        tv = view.findViewById(R.id.buttondel);
        tv2 = view.findViewById(R.id.buttonAdd);
        if(tv.getVisibility()==View.GONE && tv2.getVisibility()==View.GONE) {
            tv.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);


        }else if(tv.getVisibility()==View.VISIBLE && tv2.getVisibility()==View.VISIBLE ) {
            tv.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
        }

    }

    public static void hideButtons() {


       tv2.setVisibility(View.GONE);
       tv.setVisibility(View.GONE);

        }

    }



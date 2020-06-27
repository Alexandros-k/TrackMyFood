package com.example.alex.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 2/14/2018.
 */

public class AddFoodFragment extends Fragment {

    EditText foodName ;
    EditText foodGram;
    String name;
    int gram;
    Date now;
    MyDBHandler dbHandler;
    DailyDbHelper dailyDbHelper;
    SimpleDateFormat dateFormatter;

    private static final String TAG="AddFoodFragment";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addfood,container,false);

        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        View view= getView();
        foodName = view.findViewById(R.id.addFoodNameId);
        foodGram = view.findViewById(R.id.addGramFoodId);



        Button mButton1 = view.findViewById(R.id.addFoodId);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = foodName.getText().toString();
                gram = Integer.parseInt(foodGram.getText().toString());
                addFood(name,gram);
            }
        });

    }

    public void addFood(String name, int gram){

        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y");
        String day = dateFormatter.format(now);



        MyDBHandler dbHandler = new MyDBHandler(getContext(), null, null, 1);
        DailyDbHelper dailyDbHelper = new DailyDbHelper(getContext(), null, null, 1);

        Food f1 = dbHandler.findHandler(name);

        if(f1==null){

            Intent intent =new Intent(getContext(), FoodRegistration.class);
            intent.putExtra("Name",name);
            intent.putExtra("Gram",gram);

            startActivity(intent);



        }else {
            Food foodCopy = dbHandler.findHandler(f1.getName());
            dailyDbHelper.makeTable(day,dailyDbHelper);



            dailyDbHelper.addHandler(foodCopy);

            Toast.makeText(getContext(), "Food saved!",
                    Toast.LENGTH_LONG).show();

            startActivity(new Intent(getContext(), MainActivity.class));

        }




    }


}

package com.example.alex.myapplication;

import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.util.DisplayMetrics;
import android.support.v7.app.AppCompatActivity;
import com.example.alex.myapplication.Models.Food;
import com.example.alex.myapplication.Database.DatabaseHandler;
import com.example.alex.myapplication.Fragments.FoodLibraryFragment;
import com.example.alex.myapplication.CustomAdapters.FoodLibraryCustomAdapter;

public class PopUp extends AppCompatActivity {
    DatabaseHandler dbHandler;
    PopupWindow popUpWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        popUpWindow = new PopupWindow(this);
        DisplayMetrics dm = new DisplayMetrics();
        final int position = getIntent().getIntExtra("position", 0);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));
        Button svbtn = findViewById(R.id.SaveBtn);
        svbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.tvId);
                int graml = Integer.parseInt(et.getText().toString());
                calculateGraml(graml, v, position);
                FoodLibraryFragment.hideButtons();
                finish();
            }
        });
    }

    private void calculateGraml(int graml, View v, int position) {
        dbHandler = new DatabaseHandler(v.getContext(), null, null, 1);
        final ArrayList<Food> foodList = dbHandler.loadFoodsHandler();
        Food food = foodList.get(position);
        int fg = food.getGram();
        int kc = food.getKcal();
        int p = food.getProtein();
        int c = food.getCarbs();
        int f = food.getFats();
        Food f1 = calculateMn(food.getName(), graml, fg, kc, p, c, f);
        dbHandler.addTodayFoodHandler(f1);
        FoodLibraryCustomAdapter ca3 = new FoodLibraryCustomAdapter(getApplicationContext());
        ca3.notifyDataSetChanged();
    }

    private Food calculateMn(String name, int newGram, int oldGram, int kc, int p, int c, int f) {
        int x1 = newGram * kc / oldGram;
        int x2 = newGram * p / oldGram;
        int x3 = newGram * c / oldGram;
        int x4 = newGram * f / oldGram;
        return new Food(name, x1, newGram, x2, x3, x4);
    }
}

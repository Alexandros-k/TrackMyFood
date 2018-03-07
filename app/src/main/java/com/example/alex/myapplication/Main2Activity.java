package com.example.alex.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    private static  final String TAG ="Main2Activity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    DailyDbHelper dailyDbHelper;
    String day;



    public Main2Activity() {
        Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y", Locale.ENGLISH);
        day = dateFormatter.format(now);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());



        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);

        DailyDbHelper dailyDbHelper = new DailyDbHelper(this, null, null, 1);
        if(!dailyDbHelper.tableExists(day)){
            dailyDbHelper.makeTable(day,dailyDbHelper);

        }
        tabLayout.setupWithViewPager(mViewPager);
    }



    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
    adapter.addFragment(new MainActivityFragment(),"MAIN");
    adapter.addFragment(new TodayFoodFragment(),"TODAY FOOD");
    adapter.addFragment(new AllFoodFragment(),"FOOD LIBRARY");
    //adapter.addFragment(new AddFoodFragment(),"ADD FOOD");
viewPager.setAdapter(adapter);
}



}

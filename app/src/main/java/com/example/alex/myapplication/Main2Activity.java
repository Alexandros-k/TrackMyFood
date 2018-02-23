package com.example.alex.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

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

        mViewPager =(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout =(TabLayout)findViewById(R.id.tabs);
      /*  Date now = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("E_d_M_y");
         day = dateFormatter.format(now);
         an den ftiaksei aurio basi energopoiise to
         */
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
    adapter.addFragment(new AddFoodFragment(),"ADD FOOD");
    adapter.addFragment(new AllFoodFragment(),"FOOD LIBRARY");
viewPager.setAdapter(adapter);
}



}

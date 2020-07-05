package com.example.alex.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import com.example.alex.myapplication.Fragments.TodayFoodFragment;
import com.example.alex.myapplication.Fragments.MainActivityFragment;
import com.example.alex.myapplication.Fragments.FoodLibraryFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TodayFoodFragment.Listener {

    public String day;
    private SectionPageAdapter mSectionPageAdapter;

    public MainActivity() {
        day = Utility.getCurrentDate();
    }

    public void updateTest(String day) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        mSectionPageAdapter.addFragment(new MainActivityFragment(), "MAIN");
        mSectionPageAdapter.addFragment(new TodayFoodFragment(), "TODAY FOOD");
        mSectionPageAdapter.addFragment(new FoodLibraryFragment(), "FOOD LIBRARY");
        viewPager.setAdapter(mSectionPageAdapter);
    }
}

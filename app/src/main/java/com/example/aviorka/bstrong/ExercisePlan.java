package com.example.aviorka.bstrong;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ExercisePlan extends AppCompatActivity {

    //    private SectionsPagerAdapter mSectionsPagerAdapter;
    private DemoCollectionPagerAdapter statePageAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TabLayout tabLayout;

    public static final int FRAGMENT_ID_SETING = 1;
    public static final int FRAGMENT_ID_PLAN = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan);
        //TODO Get user name to display on the header (Title)
        setTitle(getResources().getString(R.string.app_name) + " - " +" YOSSI MITSU"); //TODO read full name from Trainee
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}

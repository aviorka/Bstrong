package com.example.aviorka.bstrong;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ExercisePlan extends AppCompatActivity {

    //    private SectionsPagerAdapter mSectionsPagerAdapter;
    private CollectionPagerAdapter statePageAdapter;


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
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the primary sections of the activity.
        statePageAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
    }

    //Page adapter that hold 2 fragment : User info & Plan info
    public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
        private final static int PAGE_COUNT = 2;

        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            int pageId = roleAuth.getPageId(i);
            switch(pageId)
            {
                case 1:
                    fragment = new OrderFragment();
                    break;
                case 2:
                    fragment = new KitchenFragment();
                    break;
            }

            Bundle args = new Bundle();
//            args.putInt(PlaceholderFragment.ARG_SECTION_NUMBER, i+1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return roleAuth.getPageCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int pageId = roleAuth.getPageId(position);
            switch(pageId){
                case Restaurant.FRAGMENT_ID_ORDER:
                    return getResources().getString(R.string.orderFrag_title);
                case Restaurant.FRAGMENT_ID_KITCHEN:
                    return getResources().getString(R.string.kitchenFrag_title);
            }
            return "ERROR " + (position);
        }
    }


}

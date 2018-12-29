package org.vitaltransformation.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.vitaltransformation.R;
import org.vitaltransformation.adapters.DrawerAdapter;
import org.vitaltransformation.adapters.DrawerAdapter.OnDrawerAdapterInteractionListener;
import org.vitaltransformation.fragments.ClassesFragment;
import org.vitaltransformation.fragments.EventsFragment;
import org.vitaltransformation.fragments.HomeFragment;
import org.vitaltransformation.fragments.HomeFragment.OnHomeFragmentInteractionListener;
import org.vitaltransformation.utils.BaseActivity;

public class MainActivity extends BaseActivity implements OnHomeFragmentInteractionListener,
        View.OnClickListener, OnDrawerAdapterInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerDrawer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DrawerAdapter(this));

        toolbar.findViewById(R.id.menu).setOnClickListener(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        View tabOne = LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        TextView title = tabOne.findViewById(R.id.title);
        title.setText("Home");
        ImageView icon = tabOne.findViewById(R.id.icon);
        icon.setBackgroundResource(R.drawable.ic_tab_home);
        tabLayout.getTabAt(0).setCustomView(tabOne).setTag(HomeFragment.TAG);

        View tabTwo = LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        title = tabTwo.findViewById(R.id.title);
        title.setText("Classes");
        icon = tabTwo.findViewById(R.id.icon);
        icon.setBackgroundResource(R.drawable.ic_tab_classes);
        tabLayout.getTabAt(1).setCustomView(tabTwo).setTag(ClassesFragment.TAG);

        View tabThree = LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        title = tabThree.findViewById(R.id.title);
        title.setText("Events");
        icon = tabThree.findViewById(R.id.icon);
        icon.setBackgroundResource(R.drawable.ic_tab_event);
        tabLayout.getTabAt(2).setCustomView(tabThree).setTag(EventsFragment.TAG);

        View tabFour = LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        title = tabFour.findViewById(R.id.title);
        title.setText("Podcast");
        icon = tabFour.findViewById(R.id.icon);
        icon.setBackgroundResource(R.drawable.ic_tab_podcast);
        tabLayout.getTabAt(3).setCustomView(tabFour).setTag("ABCD");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        View toolbarView = LayoutInflater.from(this).inflate(R.layout.toolbar_layout, null);
//        toolbar.addView(toolbarView);
    }

    @Override
    public void viewAllClasses() {
        if (mViewPager != null)
            mViewPager.setCurrentItem(1, true);
    }

    @Override
    public void viewAllEvents() {
        if (mViewPager != null)
            mViewPager.setCurrentItem(2, true);
    }

    @Override
    public void onClick(View v) {
        if (!drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    @Override
    public void onItemClicked(int position) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return ClassesFragment.newInstance();
                case 2:
                    return EventsFragment.newInstance();
                case 3:
                    return new Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }

    boolean isBackPressed = false;

    @Override
    public void onBackPressed() {

        if (isBackPressed) {
            super.onBackPressed();
        }

        isBackPressed = true;
        showMessage(getString(R.string.press_back_to_exit));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 1500);
    }
}

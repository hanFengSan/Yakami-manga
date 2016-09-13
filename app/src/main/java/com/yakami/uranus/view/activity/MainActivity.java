package com.yakami.uranus.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.yakami.uranus.R;
import com.yakami.uranus.base.BaseActivity;
import com.yakami.uranus.utils.Tools;
import com.yakami.uranus.view.fragment.CollectionFragment;
import com.yakami.uranus.view.fragment.HistoryFragment;
import com.yakami.uranus.view.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yakami on 2015/11/28.
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CollectionFragment mCollectionFragment;
    private HistoryFragment mHistoryFragment;
    private SearchFragment mSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Activity", "create mainActivity");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initTabs();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initTabs() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        if (PagerAdapter.fragmentList.size() == 0) {
            Log.e("init", "initTabs create");
            mCollectionFragment = new CollectionFragment();
            mHistoryFragment = new HistoryFragment();
            mSearchFragment = new SearchFragment();
            pagerAdapter.addFragment(mSearchFragment, getResources().getString(R.string.search));
            pagerAdapter.addFragment(mCollectionFragment, getResources().getString(R.string.collection));
            pagerAdapter.addFragment(mHistoryFragment, getResources().getString(R.string.history));
        } else {
            Log.e("init", "initTabs exist");
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);  //设置viewPager缓存页面数量
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        Tools.toast(getContext(), String.format("%d", PagerAdapter.fragmentList.size()));
    }

//    static class PagerAdapter extends FragmentStatePagerAdapter {
//        public static final List<Fragment> fragmentList = new ArrayList<>();
//        private static final List<String> fragmentTitleList = new ArrayList<>();
//
//        public PagerAdapter(FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            fragmentList.add(fragment);
//            fragmentTitleList.add(title);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return fragmentTitleList.get(position);
//        }
//    }
    static class PagerAdapter extends FragmentPagerAdapter {

        public static final List<Fragment> fragmentList = new ArrayList<>();
        private static final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("activity", "destroy MainActivity");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("activity", "start MainActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("activity", "resume MainActivity");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("activity", "pause MainActivity");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("activity", "stop MainActivity");
    }

}
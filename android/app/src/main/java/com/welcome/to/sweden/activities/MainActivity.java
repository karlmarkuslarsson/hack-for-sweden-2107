package com.welcome.to.sweden.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.adapters.MainViewPagerAdapter;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.helpers.CurrencyHelper;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.helpers.LocationHelper;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final int OFF_SCREEN_PAGES = 3;

    private MainViewPagerAdapter mAdapter;

    @BindView(R.id.activity_main_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.activity_main_tab_layout)
    TabLayout mTabBar;

    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;

    @Inject
    Cache mCache;

    @Inject
    DataHelper mDataHelper;

    @Inject
    LocationHelper mLocationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerUtils.getComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViews();

        String currency = CurrencyHelper.getCurrency(this);
        Timber.e(currency);
    }

    private void setupViews() {
        setSupportActionBar(mToolbar);
        setupViewPager();
        initializeDummyMap();
    }

    private void initializeDummyMap() {
        MapsInitializer.initialize(this);
        MapView mapView = new MapView(this);
        mapView.onCreate(null);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
            }
        });
    }

    private void setupViewPager() {
        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(OFF_SCREEN_PAGES);
        mTabBar.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationHelper.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_restart:
                restart();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void restart() {
        mDataHelper.clear();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

}

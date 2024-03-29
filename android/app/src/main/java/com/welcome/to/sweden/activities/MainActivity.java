package com.welcome.to.sweden.activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.adapters.MainViewPagerAdapter;
import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.helpers.DataHelper;
import com.welcome.to.sweden.helpers.LocationHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Inject
    FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerUtils.getComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics.setCurrentScreen(this, MainActivity.class.getSimpleName(), null);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViews();
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
            case R.id.main_menu_feedback:
                feedback();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void feedback() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
        intent.setData(Uri.parse("mailto:karl.markus.larsson@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback - Welcome to Sweden");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    private void restart() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_restart);
        View ok = dialog.findViewById(R.id.dialog_ok);
        View cancel = dialog.findViewById(R.id.dialog_cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataHelper.clear();
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}

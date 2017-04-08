package sweden.hack.userinfo.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.adapters.MainViewPagerAdapter;
import sweden.hack.userinfo.di.DaggerUtils;
import sweden.hack.userinfo.helpers.CurrencyHelper;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.helpers.LocationHelper;

public class MainActivity extends AppCompatActivity {

    private MainViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabBar;
    private Toolbar mToolbar;

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
        initViews();
        sendNotification();
        setupViews();

        String currency = CurrencyHelper.getCurrency(this);
        Log.e("", currency);
    }

    private void setupViews() {
        setSupportActionBar(mToolbar);
        setupViewPager();
    }

    private void setupViewPager() {
        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabBar.setupWithViewPager(mViewPager);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.activity_main_view_pager);
        mTabBar = (TabLayout) findViewById(R.id.activity_main_tab_layout);
        mToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
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

    private void sendNotification() {
        String KEY_TEXT_REPLY = "key_text_reply";
        String replyLabel = "replay";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(0, "Okay", null)
                        .addRemoteInput(remoteInput)
                        .build();
        NotificationCompat.Action action2 =
                new NotificationCompat.Action.Builder(0,
                        "Bad", null)
                        .addRemoteInput(remoteInput)
                        .build();
        NotificationCompat.Action action3 =
                new NotificationCompat.Action.Builder(0,
                        "Awesome!", null)
                        .addRemoteInput(remoteInput)
                        .build();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_ic_trans_arrow)
                        .setContentTitle("How did you enjoy \"Skansen\"?")
                        .addAction(action2)
                        .addAction(action)
                        .addAction(action3);


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        //mNotificationManager.notify(1, mBuilder.build());
    }

}

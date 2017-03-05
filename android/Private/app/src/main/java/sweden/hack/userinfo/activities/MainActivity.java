package sweden.hack.userinfo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.adapters.MainRecyclerViewAdapter;
import sweden.hack.userinfo.helpers.LocationHelper;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.GenderCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class MainActivity extends AppCompatActivity {

    private LocationHelper mLocationHelper;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MainRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupViews();
        mLocationHelper = new LocationHelper(Cache.sharedInstance());
        reloadData();
    }

    private void setupViews() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
            }
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new MainRecyclerViewAdapter(new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void reloadData() {
        mAdapter.reset();
        addGenderCard();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void addGenderCard() {
        mAdapter.addCard(new GenderCard(Cache.sharedInstance().getPersonNumber()));
    }

    private void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_to_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);
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

}

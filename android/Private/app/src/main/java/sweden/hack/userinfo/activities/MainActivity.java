package sweden.hack.userinfo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.adapters.MainRecyclerViewAdapter;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.helpers.LocationHelper;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.objects.main.GenderCard;
import sweden.hack.userinfo.objects.main.IncomeCard;
import sweden.hack.userinfo.objects.main.PopulationCard;
import sweden.hack.userinfo.objects.main.base.MainCard;
import timber.log.Timber;

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
        addPopulationCard();
        addIncomeCard();
        // network requests...
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void addPopulationCard() {
        HackOfSwedenApi.sharedInstance().getPopulation(new Callback<List<Population>>() {
            @Override
            public void onSuccess(@NonNull APIResponse<List<Population>> response) {
                Timber.d("Hejhej %s", response.getContent().size());

                List<Population> populations = response.getContent();
                mAdapter.addCard(new PopulationCard(populations));
            }

            @Override
            public void onFailure(@NonNull APIResponse<List<Population>> response) {

            }
        });
    }

    private void addIncomeCard() {
        HackOfSwedenApi.sharedInstance().getIncome(new Callback<List<Income>>() {
            @Override
            public void onSuccess(@NonNull APIResponse<List<Income>> response) {
                List<Income> incomes = response.getContent();
                mAdapter.addCard(new IncomeCard(incomes));
            }

            @Override
            public void onFailure(@NonNull APIResponse<List<Income>> response) {

            }
        });
    }

    private void addGenderCard() {
        mAdapter.addCard(new GenderCard(DataHelper.getUserPersonNumber()));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_logout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        DataHelper.setUserPersonNumber(null);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}

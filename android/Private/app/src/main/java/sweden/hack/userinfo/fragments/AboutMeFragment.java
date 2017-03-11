package sweden.hack.userinfo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.adapters.MainRecyclerViewAdapter;
import sweden.hack.userinfo.helpers.DataHelper;
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

public class AboutMeFragment extends Fragment {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MainRecyclerViewAdapter mAdapter;
    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_about_me, container, false);
        initViews();
        setupViews();
        reloadData();
        return mRoot;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRoot.findViewById(R.id.activity_main_swipe_to_refresh);
        mRecyclerView = (RecyclerView) mRoot.findViewById(R.id.activity_main_recycler_view);
    }

}

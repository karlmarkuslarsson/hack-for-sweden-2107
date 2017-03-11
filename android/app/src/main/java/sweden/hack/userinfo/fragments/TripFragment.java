package sweden.hack.userinfo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.adapters.MainRecyclerViewAdapter;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class TripFragment extends Fragment {

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    protected MainRecyclerViewAdapter mAdapter;
    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_trip, container, false);
        initViews();
        setupViews();
        loadData();
        return mRoot;
    }

    private void setupViews() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new MainRecyclerViewAdapter(getListener());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void loadData() {

    }

    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }
        };
    }

    private void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRoot.findViewById(R.id.fragment_main_swipe_to_refresh);
        mRecyclerView = (RecyclerView) mRoot.findViewById(R.id.fragment_main_recycler_view);
    }

}

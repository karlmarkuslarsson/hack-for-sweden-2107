package com.welcome.to.sweden.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.adapters.MainRecyclerViewAdapter;
import com.welcome.to.sweden.listeners.MainCardListener;

public abstract class BaseFragment extends Fragment {

    @BindView(R.id.fragment_main_swipe_to_refresh)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.fragment_main_recycler_view)
    public RecyclerView mRecyclerView;

    private View mRoot;

    protected MainRecyclerViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, mRoot);

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
        mAdapter = new MainRecyclerViewAdapter(getListener());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    protected abstract void reloadData();

    protected abstract MainCardListener getListener();

}


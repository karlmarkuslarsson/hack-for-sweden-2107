package com.welcome.to.sweden.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.fragments.base.BaseFragment;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.network.HackOfSwedenApi;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class AboutFragment extends BaseFragment {

    @Inject
    HackOfSwedenApi mHackOfSwedenApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerUtils.getComponent(getContext()).inject(this);
    }

    @Override
    protected void reloadData() {
        mAdapter.reset();

        // network requests...
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }

            @Override
            public void dismissCard(MainCard card) {

            }
        };
    }

}

package com.welcome.to.sweden.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.fragments.base.BaseFragment;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.network.HackOfSwedenApi;

import javax.inject.Inject;

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
            public void onCardClick(Card card) {

            }

            @Override
            public void dismissCard(Card card) {

            }
        };
    }

}

package com.welcome.to.sweden.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.welcome.to.sweden.di.DaggerUtils;
import com.welcome.to.sweden.fragments.base.BaseFragment;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.listeners.MainCardListeners;
import com.welcome.to.sweden.network.HackOfSwedenLocalFilesApi;

import javax.inject.Inject;

public class AboutFragment extends BaseFragment {

    @Inject
    HackOfSwedenLocalFilesApi mHackOfSwedenLocalFilesApi;

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
        return MainCardListeners.dummy();
    }

}

package sweden.hack.userinfo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import sweden.hack.userinfo.di.DaggerUtils;
import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.objects.main.base.MainCard;

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

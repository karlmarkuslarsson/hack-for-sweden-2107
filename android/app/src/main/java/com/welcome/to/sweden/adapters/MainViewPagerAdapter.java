package com.welcome.to.sweden.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.welcome.to.sweden.R;
import com.welcome.to.sweden.fragments.PracticalInfoFragment;
import com.welcome.to.sweden.fragments.TripFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public MainViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new TripFragment();
                break;
            case 1:
                fragment = new PracticalInfoFragment();
                break;
            default:
                throw new RuntimeException("TWF");
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int titleId;
        switch (position) {
            case 0:
                titleId = R.string.main_tab_trip;
                break;
            case 1:
                titleId = R.string.main_tab_travle_guide;
                break;
            default:
                throw new RuntimeException("TWF");
        }
        return mContext.getString(titleId);
    }
}

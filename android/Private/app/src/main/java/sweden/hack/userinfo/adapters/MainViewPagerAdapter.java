package sweden.hack.userinfo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.fragments.AboutMeFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new AboutMeFragment();
                break;
            case 1:
                fragment = new AboutMeFragment();
                break;
            case 2:
            default:
                fragment = new AboutMeFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int titleId;
        switch (position) {
            case 0:
                titleId = R.string.main_tab_today;
                break;
            case 1:
                titleId = R.string.main_tab_near;
                break;
            case 2:
            default:
                titleId = R.string.main_tab_about_me;
                break;
        }
        return CustomApplication.sharedInstance().getString(titleId);
    }
}

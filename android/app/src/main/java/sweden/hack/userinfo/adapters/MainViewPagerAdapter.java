package sweden.hack.userinfo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.fragments.PracticalInfoFragment;
import sweden.hack.userinfo.fragments.TodoFragment;
import sweden.hack.userinfo.fragments.TripFragment;

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
                fragment = new TripFragment();
                break;
            case 1:
                fragment = new TodoFragment();
                break;
            case 2:
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
                titleId = R.string.main_tab_events;
                break;
            case 2:
                titleId = R.string.main_tab_practical_info;
                break;
            default:
                throw new RuntimeException("TWF");
        }
        return CustomApplication.sharedInstance().getString(titleId);
    }
}

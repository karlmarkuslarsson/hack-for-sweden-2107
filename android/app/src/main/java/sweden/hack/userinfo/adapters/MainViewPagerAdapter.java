package sweden.hack.userinfo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.fragments.AboutFragment;
import sweden.hack.userinfo.fragments.PracticalInfoFragment;
import sweden.hack.userinfo.fragments.TodoFragment;

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
                fragment = new TodoFragment();
                break;
            case 1:
                fragment = new PracticalInfoFragment();
                break;
            case 2:
            default:
                fragment = new AboutFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int titleId;
        switch (position) {
            case 0:
                titleId = R.string.main_tab_todo;
                break;
            case 1:
                titleId = R.string.main_tab_practical_info;
                break;
            case 2:
            default:
                titleId = R.string.main_tab_about;
                break;
        }
        return CustomApplication.sharedInstance().getString(titleId);
    }
}

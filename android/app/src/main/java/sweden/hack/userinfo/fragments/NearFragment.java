package sweden.hack.userinfo.fragments;

import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.helpers.DataHelper;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.GenderCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class NearFragment extends BaseFragment {

    @Override
    protected void reloadData() {
        mAdapter.reset();
        addGenderCard();
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    protected MainCardListener getListener() {
        return new MainCardListener() {
            @Override
            public void onCardClick(MainCard card) {

            }
        };
    }

    private void addGenderCard() {
        mAdapter.addCard(new GenderCard(DataHelper.getUserPersonNumber()));
    }
}

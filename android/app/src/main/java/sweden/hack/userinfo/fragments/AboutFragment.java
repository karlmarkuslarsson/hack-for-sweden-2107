package sweden.hack.userinfo.fragments;

import android.support.annotation.NonNull;

import java.util.List;

import sweden.hack.userinfo.fragments.base.BaseFragment;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.HackOfSwedenApi;
import sweden.hack.userinfo.network.response.APIResponse;
import sweden.hack.userinfo.objects.main.IncomeCard;
import sweden.hack.userinfo.objects.main.PopulationCard;
import sweden.hack.userinfo.objects.main.base.MainCard;
import timber.log.Timber;

public class AboutFragment extends BaseFragment {


    @Override
    protected void reloadData() {
        mAdapter.reset();

        addPopulationCard();
        addIncomeCard();
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

    private void addPopulationCard() {
        HackOfSwedenApi.sharedInstance().getPopulation(new Callback<List<Population>>() {
            @Override
            public void onSuccess(@NonNull APIResponse<List<Population>> response) {
                Timber.d("Hejhej %s", response.getContent().size());

                List<Population> populations = response.getContent();
                mAdapter.addCard(new PopulationCard(populations));
            }

            @Override
            public void onFailure(@NonNull APIResponse<List<Population>> response) {

            }
        });
    }

    private void addIncomeCard() {
        HackOfSwedenApi.sharedInstance().getIncome(new Callback<List<Income>>() {
            @Override
            public void onSuccess(@NonNull APIResponse<List<Income>> response) {
                List<Income> incomes = response.getContent();
                mAdapter.addCard(new IncomeCard(incomes));
            }

            @Override
            public void onFailure(@NonNull APIResponse<List<Income>> response) {

            }
        });
    }

}

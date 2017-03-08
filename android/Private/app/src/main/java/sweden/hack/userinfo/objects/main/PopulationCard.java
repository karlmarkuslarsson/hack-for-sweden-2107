package sweden.hack.userinfo.objects.main;

import java.util.List;

import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class PopulationCard extends MainCard {

    private final List<Population> mPopulations;

    public PopulationCard(List<Population> populations) {
        mPopulations = populations;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_POPULATION;
    }

    public List<Population> getPopulations() {
        return mPopulations;
    }
}

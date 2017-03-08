package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.objects.main.PopulationCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class PopulationViewHolder extends MainViewHolder {

    private final TextView mTextView;

    public PopulationViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text_population);
    }

    @Override
    public void init(MainCard card, MainCardListener listener) {
        PopulationCard populationCard = (PopulationCard) card;
        Population pop1 = populationCard.getPopulations().get(0);
        Population pop2 = populationCard.getPopulations().get(1);
        mTextView.setText(String.format("Pop: %s, year: %s", pop1.getPopulation(), pop1.getYear()) + "\n" +
                String.format("Pop: %s, year: %s", pop2.getPopulation(), pop2.getYear())
        );
        listener.onCardClick(populationCard);
    }
}

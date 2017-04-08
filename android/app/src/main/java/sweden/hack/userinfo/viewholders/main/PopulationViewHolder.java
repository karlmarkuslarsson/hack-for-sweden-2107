package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.population.Population;
import sweden.hack.userinfo.objects.main.PopulationCard;

public class PopulationViewHolder extends MainViewHolder<PopulationCard> {

    @BindView(R.id.text_population)
    TextView mTextView;

    public PopulationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(PopulationCard card, MainCardListener listener) {
        Population pop1 = card.getPopulations().get(0);
        mTextView.setText(String.format("%s %s %s (%s)", pop1.getPopulation().substring(0, 1), pop1.getPopulation().substring(1, 4), pop1.getPopulation().substring(4, pop1.getPopulation().length()), pop1.getYear()));
    }

}

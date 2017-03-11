package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.EventCard;

/**
 * Created by sosv on 11/03/17.
 */

public class EventViewHolder extends MainViewHolder<EventCard> {
    private final TextView mTextTitle;
    private final TextView mTextDate;

    public EventViewHolder(View root) {
        super(root);
        mTextTitle = (TextView) itemView.findViewById(R.id.event_title);
        mTextDate = (TextView) itemView.findViewById(R.id.event_date);
    }

    @Override
    public void init(EventCard card, MainCardListener listener) {
        mTextTitle.setText(card.getEvent().getTitle());
        mTextDate.setText(card.getEvent().getDescription());
    }
}

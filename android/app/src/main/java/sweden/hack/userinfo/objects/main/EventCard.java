package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.Event;
import sweden.hack.userinfo.objects.main.base.MainCard;

/**
 * Created by sosv on 11/03/17.
 */

public class EventCard extends MainCard {
    private final Event mEvent;

    public EventCard(Event event) {
        mEvent = event;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_EVENT;
    }

    public Event getEvent() {
        return mEvent;
    }
}

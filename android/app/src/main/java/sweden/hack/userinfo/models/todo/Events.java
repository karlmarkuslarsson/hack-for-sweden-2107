package sweden.hack.userinfo.models.todo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sweden.hack.userinfo.models.CardComponent;

public class Events extends CardComponent {

    @SerializedName("events")
    private List<Event> mEvents;

    public List<Event> getEvents() {
        return mEvents;
    }
}

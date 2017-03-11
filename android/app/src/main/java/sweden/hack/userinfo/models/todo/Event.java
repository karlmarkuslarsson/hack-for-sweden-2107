package sweden.hack.userinfo.models.todo;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("start_time")
    private String mDate;

    @SerializedName("title")
    private String mTitle;

    public String getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }
}

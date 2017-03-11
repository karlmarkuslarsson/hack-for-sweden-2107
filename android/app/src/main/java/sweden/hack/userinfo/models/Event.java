package sweden.hack.userinfo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sosv on 11/03/17.
 */

public class Event extends CardComponent {

    @SerializedName("date")
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

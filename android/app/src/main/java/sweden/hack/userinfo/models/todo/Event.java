package sweden.hack.userinfo.models.todo;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("image")
    private String mImage;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("location")
    private String mLocation;

    @SerializedName("start_time")
    private String mStartTime;

    public String getImage() {
        return mImage;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getStartTime() {
        return mStartTime;
    }
}

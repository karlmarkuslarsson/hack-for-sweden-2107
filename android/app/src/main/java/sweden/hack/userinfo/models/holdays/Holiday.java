package sweden.hack.userinfo.models.holdays;

import com.google.gson.annotations.SerializedName;

public class Holiday {

    @SerializedName("date")
    private String mDate;

    @SerializedName("date_text")
    private String mDateText;

    @SerializedName("name")
    private String mName;

    @SerializedName("weekday")
    private String mWeekday;

    public String getWeekday() {
        return mWeekday;
    }

    public String getName() {
        return mName;
    }

    public String getDateText() {
        return mDateText;
    }

    public String getDate() {
        return mDate;
    }
}

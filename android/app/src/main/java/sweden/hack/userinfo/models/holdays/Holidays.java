package sweden.hack.userinfo.models.holdays;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sweden.hack.userinfo.models.CardComponent;

public class Holidays extends CardComponent {

    public static final String TYPE = "holidays";

    @SerializedName("holidays")
    private List<Holiday> mHolidays;

    public List<Holiday> getHolidays() {
        return mHolidays;
    }
}

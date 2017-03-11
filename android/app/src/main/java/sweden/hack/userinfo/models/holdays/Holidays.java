package sweden.hack.userinfo.models.holdays;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Holidays {

    @SerializedName("holidays")
    private List<Holiday> mHolidays;

    public List<Holiday> getHolidays() {
        return mHolidays;
    }
}

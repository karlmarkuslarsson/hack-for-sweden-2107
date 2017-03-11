package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationList {

    @SerializedName("StopLocation")
    private List<StopLocation> mStopLocations;

    public List<StopLocation> getStopLocations() {
        return mStopLocations;
    }
}

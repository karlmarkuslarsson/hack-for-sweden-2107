package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClosestStations {

    @SerializedName("LocationList")
    private LocationList mLocationList;

    @SerializedName("errorCode")
    private String mErrorCode;

    @SerializedName("errorText")
    private String mErrorText;

    public boolean hasStopLocations() {
        return mLocationList != null && mLocationList.getStopLocations() != null;
    }

    public List<StopLocation> getStopLocations() {
        if (mLocationList == null) {
            return null;
        }
        return mLocationList.getStopLocations();
    }

}

package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trip {

    @SerializedName("dur")
    private String mDuration;

    @SerializedName("chg")
    private String mChanges;

    @SerializedName("PriceInfo")
    private PriceInfo mPriceInfo;

    @SerializedName("LegList")
    private List<Leg> mLegList;

    public String getDuration() {
        return mDuration;
    }

    public String getChanges() {
        return mChanges;
    }

    public PriceInfo getPriceInfo() {
        return mPriceInfo;
    }

    public List<Leg> getLegList() {
        return mLegList;
    }
}

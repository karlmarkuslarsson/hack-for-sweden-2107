package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

public class Site extends SiteInfo {

    @SerializedName("Name") // for message
    private String mName;

    @SerializedName("SiteId")
    private String mSiteId;

    @SerializedName("Type")
    private String mType;

    @SerializedName("X")
    private String mXCoordinate;

    @SerializedName("Y")
    private String mYCoordinate;

    @Override
    public String getName() {
        return mName;
    }

    public String getSiteId() {
        return mSiteId;
    }

}

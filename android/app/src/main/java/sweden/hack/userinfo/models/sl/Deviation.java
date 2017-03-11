package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Deviation implements Serializable {

    @SerializedName("Created") // for message
    private String mCreated;

    @SerializedName("MainNews")
    private boolean mMainNews;

    @SerializedName("Header")
    private String mTitle;

    @SerializedName("Details")
    private String mDetails;

    @SerializedName("Scope")
    private String mScope;

    @SerializedName("ScopeElements")
    private String mScopeElements;

    @SerializedName("FromDateTime")
    private String mFromDateTime;

    @SerializedName("UpToDateTime")
    private String mUpToDateTime;

    @SerializedName("Updated")
    private String mUpdated;

    public String getTitle() {
        return mTitle;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getScope() {
        return mScope;
    }

    public String getFromDateTime() {
        return mFromDateTime;
    }

    public String getUpToDateTime() {
        return mUpToDateTime;
    }

    public String getUpdated() {
        return mUpdated;
    }

}

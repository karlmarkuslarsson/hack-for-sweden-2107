package com.welcome.to.sweden.models.sl;

import com.google.gson.annotations.SerializedName;

public class StopLocation extends SiteInfo {

    @SerializedName("idx")
    private int mIdx; //sorting id

    @SerializedName("name")
    private String mName;

    @SerializedName("id")
    private String mSiteId;

    @SerializedName("lat")
    private float mLat;

    @SerializedName("lon")
    private float mLng;

    @SerializedName("dist")
    private int mDist;

    @Override
    public String getName() {
        return mName;
    }

    public float getLat() {
        return mLat;
    }

    public float getLng() {
        return mLng;
    }

    public int getDist() {
        return mDist;
    }

    public String getSiteId() {
        if (mSiteId != null) {
            if (mSiteId.length() > 4) {
                return mSiteId.substring(4);
            }
        }
        return mSiteId;
    }

}

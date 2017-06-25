package com.welcome.to.sweden.models;

import com.google.gson.annotations.SerializedName;

public class TripEvent extends TripLocation {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("img")
    private String mImage;

    @SerializedName("duration")
    private Integer mDuration;

    @SerializedName("price")
    private String mPrice;

    @SerializedName("lat")
    private Float mLatitude;

    @SerializedName("lng")
    private Float mLongitude;

    @SerializedName("photo")
    private Boolean mPhoto;

    @SerializedName("tag")
    private String mTag;

    @SerializedName("min_temp")
    private Integer mMinTemp;

    @SerializedName("max_temp")
    private Integer mMaxTemp;

    // First month it is opened
    @SerializedName("first_month")
    private Integer mFirstMonth;

    // Last month it is opened
    @SerializedName("last_month")
    private Integer mLastMonth;

    @SerializedName("openingHourFrom")
    private Integer mOpeningHourFrom;

    @SerializedName("openingHourTo")
    private Integer mOpeningHourTo;

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public Integer getDuration() {
        return mDuration;
    }

    public void setDuration(Integer mDuration) {
        this.mDuration = mDuration;
    }

    @Override
    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    @Override
    public float getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Float mLatitude) {
        this.mLatitude = mLatitude;
    }

    @Override
    public float getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Float mLongitude) {
        this.mLongitude = mLongitude;
    }

    public Boolean getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Boolean mPhoto) {
        this.mPhoto = mPhoto;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public int getMinTemp() {
        if (mMinTemp == null) {
            return -1000;
        }
        return mMinTemp;
    }

    public Integer getMaxTemp() {
        if (mMaxTemp == null) {
            return 1000;
        }
        return mMaxTemp;
    }

    public Integer getFirstMonth() {
        return mFirstMonth;
    }

    public Integer getLastMonth() {
        return mLastMonth;
    }

    public Integer getOpeningHourFrom() {
        return mOpeningHourFrom;
    }

    public Integer getOpeningHourTo() {
        return mOpeningHourTo;
    }

}

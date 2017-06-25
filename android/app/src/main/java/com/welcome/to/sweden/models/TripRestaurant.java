package com.welcome.to.sweden.models;

import com.google.gson.annotations.SerializedName;

public class TripRestaurant extends TripLocation {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("img")
    private String mImage;

    @SerializedName("lat")
    private Float mLatitude;

    @SerializedName("lng")
    private Float mLongitude;

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

    @Override
    public String getPrice() {
        return null;
    }

    @Override
    public Integer getDuration() {
        return null;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public float getLatitude() {
        return mLatitude;
    }

    @Override
    public float getLongitude() {
        return mLongitude;
    }

    @Override
    public String getTag() {
        return "restaurant";
    }

    public void setLatitude(Float latitude) {
        mLatitude = latitude;
    }

    public void setLongitude(Float longitude) {
        mLongitude = longitude;
    }

}

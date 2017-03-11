package sweden.hack.userinfo.models.myTrip;

import com.google.gson.annotations.SerializedName;

public class MyTripEvent {

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

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    public Integer getDuration() {
        return mDuration;
    }

    public void setDuration(Integer mDuration) {
        this.mDuration = mDuration;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public Float getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Float mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Float getLongitude() {
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
}

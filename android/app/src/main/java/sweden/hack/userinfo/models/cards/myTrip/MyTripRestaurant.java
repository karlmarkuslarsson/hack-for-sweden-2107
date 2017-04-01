package sweden.hack.userinfo.models.cards.myTrip;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sosv on 11/03/17.
 */

public class MyTripRestaurant extends MyTripLatLng {

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

    @Override
    public float getLatitude() {
        return mLatitude;
    }

    @Override
    public float getLongitude() {
        return mLongitude;
    }
}

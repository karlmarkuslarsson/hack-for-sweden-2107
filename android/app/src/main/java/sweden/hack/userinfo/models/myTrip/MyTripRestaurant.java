package sweden.hack.userinfo.models.myTrip;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sosv on 11/03/17.
 */

class MyTripRestaurant {
    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("img")
    private String mImage;

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
}

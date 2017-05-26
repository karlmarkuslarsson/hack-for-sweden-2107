package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

class TariffZone {

    @SerializedName("$")
    private String mCost;

    public String getCost() {
        return mCost;
    }

}

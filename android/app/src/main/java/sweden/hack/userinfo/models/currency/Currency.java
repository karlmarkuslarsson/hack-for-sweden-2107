package sweden.hack.userinfo.models.currency;

import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("value")
    private String mValue;

    public String getValue() {
        return mValue;
    }

}

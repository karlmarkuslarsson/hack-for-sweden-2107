package sweden.hack.userinfo.models.currency;

import com.google.gson.annotations.SerializedName;

import sweden.hack.userinfo.models.CardComponent;

public class Currency extends CardComponent {

    @SerializedName("text")
    private String mValue;

    public String getValue() {
        return mValue;
    }

}

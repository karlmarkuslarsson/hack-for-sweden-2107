package sweden.hack.userinfo.models.cards;

import com.google.gson.annotations.SerializedName;

public class CurrentCurrency extends CardComponent {

    @SerializedName("text")
    private String mValue;

    public String getValue() {
        return mValue;
    }

}

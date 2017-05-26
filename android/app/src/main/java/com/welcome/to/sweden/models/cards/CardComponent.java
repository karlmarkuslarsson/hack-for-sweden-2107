package sweden.hack.userinfo.models.cards;

import com.google.gson.annotations.SerializedName;

import sweden.hack.userinfo.enums.ComponentCardType;

public class CardComponent {
    @SerializedName("type")
    private ComponentCardType mType;

    public ComponentCardType getType() {
        return mType;
    }
}

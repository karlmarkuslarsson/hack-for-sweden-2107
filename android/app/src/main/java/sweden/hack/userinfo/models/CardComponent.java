package sweden.hack.userinfo.models;

import com.google.gson.annotations.SerializedName;

import sweden.hack.userinfo.enums.ComponentCardType;

/**
 * Created by sosv on 11/03/17.
 */

public class CardComponent {
    @SerializedName("type")
    private ComponentCardType mType;

    public ComponentCardType getType() {
        return mType;
    }
}

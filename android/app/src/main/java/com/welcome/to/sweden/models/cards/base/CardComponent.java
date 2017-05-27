package com.welcome.to.sweden.models.cards.base;

import com.google.gson.annotations.SerializedName;
import com.welcome.to.sweden.enums.ComponentCardType;

public class CardComponent extends Card {
    @SerializedName("type")
    private ComponentCardType mType;
}

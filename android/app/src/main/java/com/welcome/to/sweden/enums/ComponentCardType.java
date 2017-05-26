package com.welcome.to.sweden.enums;

import com.google.gson.annotations.SerializedName;

import com.welcome.to.sweden.models.cards.CurrentCurrency;
import com.welcome.to.sweden.models.cards.holdays.Holidays;
import com.welcome.to.sweden.models.cards.phrases.Phrases;

public enum ComponentCardType {
    @SerializedName("currency")
    CURRENCY("currency", CurrentCurrency.class),

    @SerializedName("holidays")
    HOLIDAYS("holidays", Holidays.class),

    @SerializedName("phrase")
    PHRASE("phrase", Phrases.class);

    private final String mType;
    private final Class mObject;

    ComponentCardType(String type, Class object) {
        mType = type;
        mObject = object;
    }

    public static Class getClassFromType(String type) {
        if(type != null && !type.isEmpty()) {
            for(ComponentCardType cardTypes: ComponentCardType.values()) {
                if(cardTypes.mType.equals(type)) {
                    return cardTypes.mObject;
                }
            }
        }
        return null;
    }
}

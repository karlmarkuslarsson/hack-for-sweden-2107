package sweden.hack.userinfo.enums;

import com.google.gson.annotations.SerializedName;

import sweden.hack.userinfo.models.cards.CurrentCurrency;
import sweden.hack.userinfo.models.cards.holdays.Holidays;
import sweden.hack.userinfo.models.cards.phrases.Phrases;
import sweden.hack.userinfo.models.todo.Events;

/**
 * Created by sosv on 11/03/17.
 */

public enum ComponentCardType {
    @SerializedName("currency")
    CURRENCY("currency", CurrentCurrency.class),

    @SerializedName("holidays")
    HOLIDAYS("holidays", Holidays.class),

    @SerializedName("phrase")
    PHRASE("phrase", Phrases.class),

    @SerializedName("events")
    EVENTS("events", Events.class);

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

package sweden.hack.userinfo.enums;

import com.google.gson.annotations.SerializedName;

import sweden.hack.userinfo.models.currency.Currency;
import sweden.hack.userinfo.models.holdays.Holidays;
import sweden.hack.userinfo.models.phrases.Phrases;
import sweden.hack.userinfo.models.todo.Events;

/**
 * Created by sosv on 11/03/17.
 */

public enum ComponentCardType {
    @SerializedName("currency")
    CURRENCY("currency", Currency.class),

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

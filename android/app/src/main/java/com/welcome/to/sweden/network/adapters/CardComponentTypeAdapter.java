package com.welcome.to.sweden.network.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;

import com.welcome.to.sweden.enums.ComponentCardType;
import com.welcome.to.sweden.models.cards.CardComponent;
import com.welcome.to.sweden.models.NoneComponent;

/**
 * Created by sosv on 11/03/17.
 */

public class CardComponentTypeAdapter implements JsonDeserializer<CardComponent> {

    private static final String TYPE_KEY = "type";

    @Override
    public CardComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(TYPE_KEY);
        String type = prim.getAsString().toLowerCase();
        Class<?> clazz = ComponentCardType.getClassFromType(type);
        if (clazz == null) {
            clazz = NoneComponent.class;
        }
        return context.deserialize(jsonObject, clazz);
    }
}

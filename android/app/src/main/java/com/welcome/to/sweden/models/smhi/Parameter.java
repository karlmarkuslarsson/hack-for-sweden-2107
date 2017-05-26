package com.welcome.to.sweden.models.smhi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.welcome.to.sweden.enums.WeatherParamName;
import com.welcome.to.sweden.enums.WeatherSymbolEnum;

public class Parameter implements Serializable {

    @SerializedName("name")
    private WeatherParamName mName;

    @SerializedName("values")
    private List<Float> mValues;

    public WeatherParamName getName() {
        return mName;
    }

    public List<Float> getValues() {
        if (mValues == null) {
            mValues = new ArrayList<>();
        }
        if (mValues.isEmpty()) {
            mValues.add(-100f);
        }
        return mValues;
    }

    public String getValue() {
        return String.valueOf(getValues().get(0));
    }

    public String getWeatherSymbolText() {
        float value = getValues().get(0);
        return WeatherSymbolEnum.getSymbolString((int) value);
    }

}

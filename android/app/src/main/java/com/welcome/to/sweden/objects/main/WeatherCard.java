package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.smhi.Weather;
import com.welcome.to.sweden.objects.main.base.MainCard;

public class WeatherCard extends MainCard {
    private final Weather mWeather;

    public WeatherCard(Weather weather) {
        mWeather = weather;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_WEATHER;
    }

    public Weather getWeather() {
        return mWeather;
    }
}

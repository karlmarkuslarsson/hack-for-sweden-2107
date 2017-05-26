package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.models.smhi.Weather;
import com.welcome.to.sweden.objects.main.base.Card;

public class WeatherCard extends Card {
    private final Weather mWeather;

    public WeatherCard(Weather weather) {
        mWeather = weather;
    }

    public Weather getWeather() {
        return mWeather;
    }
}

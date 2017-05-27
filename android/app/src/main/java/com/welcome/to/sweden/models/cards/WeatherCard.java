package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.cards.base.Card;
import com.welcome.to.sweden.models.smhi.Weather;

public class WeatherCard extends Card {
    private final Weather mWeather;

    public WeatherCard(Weather weather) {
        mWeather = weather;
    }

    public Weather getWeather() {
        return mWeather;
    }
}

package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.smhi.Weather;
import sweden.hack.userinfo.objects.main.base.MainCard;

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

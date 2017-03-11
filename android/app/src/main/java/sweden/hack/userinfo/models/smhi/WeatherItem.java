package sweden.hack.userinfo.models.smhi;

import java.util.ArrayList;

public class WeatherItem {

    private final String mDay;
    private final ArrayList<WeatherPoint> mWeatherPoints;

    public WeatherItem(String day, ArrayList<WeatherPoint> weatherPoints) {
        mDay = day;
        mWeatherPoints = weatherPoints;
    }

    public void add(WeatherPoint point) {
        mWeatherPoints.add(point);
    }

    public CharSequence getTitle() {
        return mDay;
    }

    public ArrayList<WeatherPoint> getWeatherPoints() {
        return mWeatherPoints;
    }

}

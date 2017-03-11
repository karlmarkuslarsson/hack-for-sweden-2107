package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.smhi.Parameter;
import sweden.hack.userinfo.objects.main.WeatherCard;

public class WeatherViewHolder extends MainViewHolder<WeatherCard> {
    private final TextView mWeatherNowText;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        mWeatherNowText = (TextView) itemView.findViewById(R.id.text_weather_now);
    }

    @Override
    public void init(WeatherCard card, MainCardListener listener) {

        for (Parameter parameter :
                card.getWeather().getTimeSeries().get(0).getParameters()) {
            if (parameter.getName() != null) {
                switch (parameter.getName()) {
                    case AIR_PRESSURE:
                        break;
                    case TEMPERATURE:
                        mWeatherNowText.setText(parameter.getValue() + " \u2103");
                        break;
                    case VISIBILITY:
                        break;
                    case WIND_DIRECTION:
                        break;
                    case WIND_SPEED:
                        break;
                    case RELATIVE_HUMIDITY:
                        break;
                    case THUNDER_PROB:
                        break;
                    case MEAN_VALUE_TOTAL_CLOUD_VALUE:
                        break;
                    case MEAN_VALUE_LOW_CLOUD_VALUE:
                        break;
                    case MEAN_VALUE_MEDIUM_CLOUD_VALUE:
                        break;
                    case MEAN_VALUE_HIGH_CLOUD_VALUE:
                        break;
                    case WIND_GUST_SPEED:
                        break;
                    case MINIMUM_PERCIPITATION:
                        break;
                    case MAXIMUM_PERCIPITATION:
                        break;
                    case PERCENT_OF_PERCIPITATION_FROZEN:
                        break;
                    case PERCIPITATION_CATEGORY:
                        break;
                    case MEAN_PERCIPITATION_ITENSITY:
                        break;
                    case MEDIAN_PERCIPITATION_ITENSITY:
                        break;
                    case WEATHER_SYMBOL:
                        break;
                }
            }
        }
    }
}

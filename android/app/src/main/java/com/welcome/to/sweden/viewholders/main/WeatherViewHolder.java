package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.smhi.Parameter;
import sweden.hack.userinfo.models.smhi.WeatherPoint;
import sweden.hack.userinfo.objects.main.WeatherCard;

public class WeatherViewHolder extends MainViewHolder<WeatherCard> {

    @BindView(R.id.text_weather_now)
    TextView mWeatherNowText;

    @BindView(R.id.text_humidity)
    TextView mHumidity;

    @BindView(R.id.text_wind)
    TextView mWindSpeed;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(WeatherCard card, MainCardListener listener) {

        WeatherPoint weatherPoint = card.getWeather().getTimeSeries().get(0);
        for (Parameter parameter : weatherPoint.getParameters()) {
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
                        mWindSpeed.setText(String.valueOf(parameter.getValue()) + " m/s");
                        break;
                    case RELATIVE_HUMIDITY:
                        mHumidity.setText(parameter.getValue() + " %");
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

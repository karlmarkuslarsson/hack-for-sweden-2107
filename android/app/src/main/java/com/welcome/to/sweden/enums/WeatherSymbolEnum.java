package sweden.hack.userinfo.enums;

import com.google.gson.annotations.SerializedName;

public enum WeatherSymbolEnum {
    CLEAR_SKY(1),
    NEARLY_CLEAR_SKY(2),
    VARIABLE_CLOUDNESS(3),
    HALFCLEAR_SCY(4),
    CLOUDY_SKY(5),
    OVERCAST(6),
    FOG(7),
    RAIN_SHOWERS(8),
    THUNDERSTORM(9),
    LIGHT_SLEET(10),
    SNOW_SHOWERS(11),
    RAIN(12),
    THUNDER(13),
    SLEET(14),
    SNOWFALL(15);

    @SerializedName("value")
    private final int mValue;

    WeatherSymbolEnum(int value) {
        mValue = value;
    }

    public static String getSymbolString(int value) {
        switch (value) {
            case 1:
                return "Clear";
            case 2:
                return "Nearly clear";
            case 3:
                return "Variable cloudness";
            case 4:
                return "Half clear";
            case 5:
                return "Cloudy";
            case 6:
                return "Very cloudy";
            case 7:
                return "Fog";
            case 8:
                return "Rain showers";
            case 9:
                return "Thunderstorm";
            case 10:
                return "Light sleet";
            case 11:
                return "Some snow";
            case 12:
                return "Rain";
            case 13:
                return "Thunder";
            case 14:
                return "Sleet";
            case 15:
                return "Snowfall";
        }
        return null;
    }
}

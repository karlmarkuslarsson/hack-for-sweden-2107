package com.welcome.to.sweden;

public class Constants {

    public static final String USER_TRIP_DATE = "prefTripDate";
    public static final String USER_HAS_STARTED = "prefHasStarted";
    public static final String USER_TRIP_DAYS = "prefTripDays";
    public static final String USER_CURRENCY = "prefCurrency";

    // SL - API
    public static final String SL_BASE_URL = "http://api.sl.se/api2/";
    public static final String SL_DEPARTURE_KEY = "304e46a3431a49e4aa87b438bcd81680";
    public static final String SL_NEARBY_KEY = "56f7be90c9d042d98b060c92e76448a8";
    public static final String SL_PLANNER = "71e352ee5d1d494a81ccc1b2774c848b";

    // Arlanda lat/lng
    public static final String ARLANDA_LAT = "59.649213";
    public static final String ARLANDA_LNG = "17.929258";

    // Centralen lat/lng
    public static final String CENTRALEN_LAT = "59.33";
    public static final String CENTRALEN_LNG = "18.06";


    // change to hack for sweden key if we´re going to use this..
    public static final String SL_DEVIATIONS_KEY = "2cab4ab3c66c4e94b88cdd2ee898ed31";
    public static final String SL_PLACE_SEARCH_KEY = "20915e6562274eada9440a467172aacf";

    // SMHI - API
    @SuppressWarnings("LongLine")
    public static final String SMHI_BASE_URL = "http://opendata-download-metfcst.smhi.se/api/category/pmp2g/version/2/geotype/point/";

    public static final String TRIP_PATHS = "prefTripPath";
    public static final String EXCHANGE_RATES_URL = "http://api.fixer.io/";
    public static final String CACHED_EXCHANGE_RATES = "prefExchangeRates";
}

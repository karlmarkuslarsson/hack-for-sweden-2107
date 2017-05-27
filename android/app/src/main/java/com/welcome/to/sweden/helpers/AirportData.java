package com.welcome.to.sweden.helpers;

import com.google.common.collect.ImmutableList;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.models.AirportAlternative;
import com.welcome.to.sweden.models.exchangerates.ExchangeRates;

import java.util.List;

public class AirportData {

    private static final String ICON_BUSS = "https://www.materialui.co/materialIcons/maps/directions_bus_grey_192x192.png";
    private static final String ICON_TRAIN = "https://www.materialui.co/materialIcons/maps/train_black_192x192.png";
    private static final String ICON_TAXI = "https://images.vexels.com/media/users/3/128966/isolated/preview/a412750ea9a1df2d9475ee6047351a19-uk-taxi-cab-icon-by-vexels.png";

    public static List<AirportAlternative> getAlternatives(ExchangeRates exchangeRates, String currency) {
        String sek280 = CurrencyHelper.toForeignCurrencyPretty(exchangeRates, currency, 280);
        String sek100 = CurrencyHelper.toForeignCurrencyPretty(exchangeRates, currency, 100);
        String sek500 = CurrencyHelper.toForeignCurrencyPretty(exchangeRates, currency, 500);
        return ImmutableList.of(
            new AirportAlternative(R.string.airport_travel_arlanda_express, ICON_TRAIN, "20min", sek280),
            new AirportAlternative(R.string.airport_travel_shuttle, ICON_BUSS, "40min", sek100),
            new AirportAlternative(R.string.airport_travel_taxi, ICON_TAXI, "30min", sek500)
        );
    }
}

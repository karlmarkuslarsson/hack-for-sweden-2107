package com.welcome.to.sweden.models.cards;

import android.support.annotation.StringRes;

import com.welcome.to.sweden.R;
import com.welcome.to.sweden.models.cards.base.Card;

import java.util.Arrays;
import java.util.List;

public class AirportCard extends Card {

    public static final String ICON_BUSS = "https://www.materialui.co/materialIcons/maps/directions_bus_grey_192x192.png";
    public static final String ICON_TRAIN = "https://www.materialui.co/materialIcons/maps/train_black_192x192.png";
    public static final String ICON_TAXI = "https://images.vexels.com/media/users/3/128966/isolated/preview/a412750ea9a1df2d9475ee6047351a19-uk-taxi-cab-icon-by-vexels.png";

    public List<Alternative> getAlternatives() {
        return Arrays.asList(
            new Alternative(R.string.airport_travel_arlanda_express, ICON_TRAIN, "20min", 280),
            new Alternative(R.string.airport_travel_shuttle, ICON_BUSS, "40min", 100),
            new Alternative(R.string.airport_travel_taxi, ICON_TAXI, "30min", 500)
        );
    }

    public static class Alternative {
        Alternative(@StringRes int title, String img, String time, int cost) {
            this.title = title;
            this.img = img;
            this.time = time;
            this.cost = cost;
        }

        private String img;
        private String time;
        private int cost;

        @StringRes private int title;

        public int getTitle() {
            return title;
        }

        public String getImg() {
            return img;
        }

        public String getTime() {
            return time;
        }

        public int getCost() {
            return cost;
        }
    }
}

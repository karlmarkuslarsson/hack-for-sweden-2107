package sweden.hack.userinfo.objects.main;

import java.util.Arrays;
import java.util.List;

import sweden.hack.userinfo.objects.main.base.MainCard;

public class AirportCard extends MainCard {

    public static final String ICON_BUSS = "https://www.materialui.co/materialIcons/maps/directions_bus_grey_192x192.png";
    public static final String ICON_TRAIN = "https://www.materialui.co/materialIcons/maps/train_black_192x192.png";
    public static final String ICON_TAXI = "https://images.vexels.com/media/users/3/128966/isolated/preview/a412750ea9a1df2d9475ee6047351a19-uk-taxi-cab-icon-by-vexels.png";

    @Override
    public int getViewType() {
        return MainCard.TYPE_AIRPORT;
    }

    public List<Alternative> getAlternatives() {
        return Arrays.asList(
            new Alternative("Arlanda Express", ICON_TRAIN, "20min", "16 USD"),
            new Alternative("Airport Shuttle", ICON_BUSS, "40min", "11 USD"),
            new Alternative("Taxi", ICON_TAXI, "30min", "55 USD")
        );
    }

    public static class Alternative {
        Alternative(String title, String img, String time, String cost) {
            this.title = title;
            this.img = img;
            this.time = time;
            this.cost = cost;
        }

        private String title;
        private String img;
        private String time;
        private String cost;

        public String getTitle() {
            return title;
        }

        public String getImg() {
            return img;
        }

        public String getTime() {
            return time;
        }

        public String getCost() {
            return cost;
        }
    }
}

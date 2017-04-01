package sweden.hack.userinfo.models.currency;

import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("name")
    private String name;

    @SerializedName("country")
    private String country;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}

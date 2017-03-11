package sweden.hack.userinfo.models.population;

import com.google.gson.annotations.SerializedName;

public class Population {

    @SerializedName("region")
    private String mRegion;

    @SerializedName("år")
    private String mYear;

    @SerializedName("folkmängd")
    private String mPopulation;


    public String getRegion() {
        return mRegion;
    }

    public String getYear() {
        return mYear;
    }

    public String getPopulation() {
        return mPopulation;
    }
}

package sweden.hack.userinfo.models.currency;

import com.google.gson.annotations.SerializedName;

public class CountryNameCodeLocale {

    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private String number;
    @SerializedName("code")
    private String code;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCode() {
        return code;
    }
}

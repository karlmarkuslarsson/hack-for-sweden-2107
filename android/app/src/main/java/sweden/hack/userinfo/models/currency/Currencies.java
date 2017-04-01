package sweden.hack.userinfo.models.currency;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Currencies {

    @SerializedName("currencies")
    private List<Currency> mList;

    public List<Currency> getValue() {
        return mList;
    }

}

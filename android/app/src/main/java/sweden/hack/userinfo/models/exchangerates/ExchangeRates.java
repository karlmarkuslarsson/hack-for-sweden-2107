package sweden.hack.userinfo.models.exchangerates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExchangeRates implements Serializable {
    @SerializedName("rates")
    public Rates mRates;
}

package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

public class PriceInfo {

    @SerializedName("TariffZones")
    private TariffZone mTariffZones;

    @SerializedName("TariffRemark")
    private TariffRemark mTariffRemark;

    public TariffZone getTariffZones() {
        return mTariffZones;
    }

    public TariffRemark getTariffRemark() {
        return mTariffRemark;
    }
}

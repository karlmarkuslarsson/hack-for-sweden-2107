package sweden.hack.userinfo.models.income;

import com.google.gson.annotations.SerializedName;

public class Income {

    @SerializedName("region")
    private String mRegion;

    @SerializedName("ålder")
    private String mAge;

    @SerializedName("inkomstklass")
    private String mIncomeClass;

    @SerializedName("år")
    private String mYear;

    @SerializedName("medianinkomst_tkr")
    private String mMedianIncome;

    @SerializedName("antal_personer")
    private String mNbrPersons;

    public String getRegion() {
        return mRegion;
    }

    public String getIncomeClass() {
        return mIncomeClass;
    }

    public String getAge() {
        return mAge;
    }

    public String getYear() {
        return mYear;
    }

    public String getMedianIncome() {
        return mMedianIncome;
    }

    public String getNbrPersons() {
        return mNbrPersons;
    }
}

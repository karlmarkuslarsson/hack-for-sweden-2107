package sweden.hack.userinfo.models.smhi;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherPoint implements Serializable {

    @SerializedName("validTime")
    private String mValidTime;

    @SerializedName("parameters")
    private List<Parameter> mParameters;

    public String getValidTime() {
        return mValidTime;
    }

    public List<Parameter> getParameters() {
        if (mParameters == null) {
            return new ArrayList<>();
        }
        return mParameters;
    }

    public DateTime getDateTime() {
        if (mValidTime == null) {
            return null;
        }
        return DateTime.parse(mValidTime);
    }

}

package sweden.hack.userinfo.models.sl;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Deviations {

    @SerializedName("StatusCode") // for message
    private String mStatusCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("ExecutionTime") //in ms
    private String mExecutionTime;

    @SerializedName("ResponseData")
    private ArrayList<Deviation> mResponseData;

    public ArrayList<Deviation> getResponseData() {
        return mResponseData;
    }
}

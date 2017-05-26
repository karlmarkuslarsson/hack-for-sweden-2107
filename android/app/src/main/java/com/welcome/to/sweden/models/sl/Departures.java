package com.welcome.to.sweden.models.sl;

import com.google.gson.annotations.SerializedName;

public class Departures {

    @SerializedName("StatusCode") // for message
    private String mStatusCode;

    @SerializedName("Message")
    private String mMessage;

    @SerializedName("ExecutionTime") //in ms
    private String mExecutionTime;

    @SerializedName("ResponseData")
    private ResponseData mResponseData;

    public ResponseData getResponseData() {
        return mResponseData;
    }


}

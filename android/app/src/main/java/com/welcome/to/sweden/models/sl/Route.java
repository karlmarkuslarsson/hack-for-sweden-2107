package com.welcome.to.sweden.models.sl;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import com.welcome.to.sweden.enums.TransportModeEnum;


public class Route {

    @SerializedName("JourneyDirection")
    private int mDirection;

    @SerializedName("GroupOfLine")
    private String mGroupOfLine; // ex blue bus

    @SerializedName("DisplayTime")
    private String mDisplayName; // ex 22 min

    @SerializedName("StopAreaName")
    private String mStopName;

    @SerializedName("ExpectedDateTime")
    private String mExpectedDateTime; //when arriving

    @SerializedName("TransportMode")
    private TransportModeEnum mTransportMode;

    @SerializedName("LineNumber")
    private String mLineNumber;

    @SerializedName("Destination")
    private String mDestination;

    public String getDestination() {
        return mDestination;
    }

    public int getDirection() {
        return mDirection;
    }

    public String getGroupOfLine() {
        if (mGroupOfLine == null) {
            mGroupOfLine = "";
        }
        return mGroupOfLine;
    }

    public String getStopName() {
        return mStopName;
    }

    public String getExpectedDateTime() {
        if (mExpectedDateTime == null) {
            int time = 1000;
            if (mDisplayName.contains("min")) {
                try {
                    time = Integer.parseInt(mDisplayName.replaceAll("min", "").trim());
                } catch (Exception ignored) {
                }
            } else if (mDisplayName.equals("Nu")) {
                time = 0;
            }
            return DateTime.now().plusMinutes(time).toString();
        }
        return mExpectedDateTime;
    }

    public TransportModeEnum getTransportMode() {
        return mTransportMode;
    }

    public String getLineNumber() {
        return mLineNumber;
    }

}

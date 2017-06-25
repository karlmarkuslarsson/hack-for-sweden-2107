package com.welcome.to.sweden.utils;

import javax.annotation.Nullable;

public class TimeUtils {
    public static String getHourAndMinutes(@Nullable Integer duration) {
        if (duration == null) {
            return "";
        }
        int dur = duration;
        int hours = dur / 60;
        dur = dur - hours * 60;
        String result = "";
        if (hours > 0) {
            result += hours + "h";
        }

        if (dur > 0) {
            if (result.length() > 0) {
                result += " ";
            }
            result += dur + "min";
        }

        return result;
    }
}

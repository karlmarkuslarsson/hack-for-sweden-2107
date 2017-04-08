package sweden.hack.userinfo;

public class TimeUtils {
    public static String getTime(Integer duration) {
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

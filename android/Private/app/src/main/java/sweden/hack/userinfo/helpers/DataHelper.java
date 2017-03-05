package sweden.hack.userinfo.helpers;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.Constants;

public class DataHelper {
    public static void setUserPersonNumber(String personNumber) {
        Cache.sharedInstance().setUserPersonNumber(personNumber);
        SharedPrefsHelper.sharedInstance().setPreference(Constants.USER_PERSON_NUMER, personNumber);
    }
}

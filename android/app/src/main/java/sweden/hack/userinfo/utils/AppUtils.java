package sweden.hack.userinfo.utils;

import android.util.DisplayMetrics;

import sweden.hack.userinfo.CustomApplication;

public class AppUtils {
    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = CustomApplication.sharedInstance()
                .getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}

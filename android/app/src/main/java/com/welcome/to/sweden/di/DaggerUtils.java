package sweden.hack.userinfo.di;

import android.content.Context;
import android.support.annotation.NonNull;

import sweden.hack.userinfo.CustomApplication;

public class DaggerUtils {

    private DaggerUtils() {

    }

    public static InjectionContainer getComponent(@NonNull Context context) {
        return ((CustomApplication) context.getApplicationContext()).getAppComponent();
    }

}

package com.welcome.to.sweden.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.welcome.to.sweden.CustomApplication;

public class DaggerUtils {

    private DaggerUtils() {

    }

    public static InjectionContainer getComponent(@NonNull Context context) {
        return ((CustomApplication) context.getApplicationContext()).getAppComponent();
    }

}

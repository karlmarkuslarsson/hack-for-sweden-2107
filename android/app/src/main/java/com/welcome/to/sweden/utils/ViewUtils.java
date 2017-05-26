package com.welcome.to.sweden.utils;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

public class ViewUtils {

    private ViewUtils() {}

    public static void text(View v, String text) {
        TextView.class.cast(v).setText(text);
    }

    public static void text(View v, @StringRes int text) {
        TextView.class.cast(v).setText(text);
    }
}

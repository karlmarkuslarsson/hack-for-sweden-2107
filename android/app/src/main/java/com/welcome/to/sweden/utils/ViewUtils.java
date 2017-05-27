package com.welcome.to.sweden.utils;

import android.content.Context;
import android.content.res.Resources;
import android.icu.lang.UCharacter;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ViewUtils {

    private ViewUtils() {}

    public static void text(View v, String text) {
        TextView.class.cast(v).setText(text);
    }

    public static void text(View v, @StringRes int text) {
        TextView.class.cast(v).setText(text);
    }

    public static String text(Context c, @StringRes int res, String... args) {
        Resources resources = c.getResources();
        return args.length == 0
                ? resources.getString(res)
                : resources.getString(res, (Object[]) args);
    }
}

package com.welcome.to.sweden.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;

public class ViewUtils {

    private ViewUtils() {}

    public static void text(View v, String text) {
        TextView.class.cast(v).setText(text);
    }

    public static void text(View v, @StringRes int text) {
        TextView.class.cast(v).setText(text);
    }

    public static String text(Context context, @StringRes int res, String... args) {
        Resources resources = context.getResources();
        return args.length == 0
                ? resources.getString(res)
                : resources.getString(res, (Object[]) args);
    }

    public static int dp2Px(View view, int dp) {
        return dp2Px(view.getContext(), dp);
    }

    public static int dp2Px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,   dp, displayMetrics);
    }
}

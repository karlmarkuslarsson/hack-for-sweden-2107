package com.welcome.to.sweden.utils;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.List;

public class SpannableUtils {


    public static SpannableString boldTitle(List<TitleValue> list, SeparatorType type) {

        List<Markup> boldMarkups = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            TitleValue titleValue = list.get(i);
            if (i != 0) {
                switch (type) {
                    case NEW_LINE:
                        sb.append("\n");
                        break;
                    case SPACE:
                        sb.append("  ");
                        break;
                }
            }
            int from = sb.length();
            String title = titleValue.mTitle;
            sb.append(title);
            sb.append(": ");
            boldMarkups.add(new Markup(from, sb.length()));
            String value = titleValue.mValue;
            sb.append(value);
        }

        SpannableString spannableString = new SpannableString(sb.toString());
        for (Markup markup : boldMarkups) {
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), markup.mFrom, markup.mTo, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    public enum SeparatorType {NEW_LINE, SPACE}

    private static class Markup {
        int mFrom;
        int mTo;

        public Markup(int from, int to) {
            mFrom = from;
            mTo = to;
        }
    }

    public static class TitleValue {
        public String mTitle;
        public String mValue;

        public TitleValue(String title, String value) {
            mTitle = title;
            mValue = value;
        }
    }
}

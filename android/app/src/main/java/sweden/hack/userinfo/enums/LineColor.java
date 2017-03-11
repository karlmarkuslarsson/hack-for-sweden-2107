package sweden.hack.userinfo.enums;

import sweden.hack.userinfo.R;

public enum LineColor {


    BLUE(R.color.light_blue, R.color.sl_light_blue),
    RED(R.color.light_red, R.color.sl_light_red),
    GREEN(R.color.light_green, R.color.sl_light_green),
    PURPLE(R.color.light_purple, R.color.sl_light_purple);

    private final int mPrimaryColor;
    private final int mLightPrimaryColor;

    LineColor(int primaryColor, int lightPrimaryColor) {
        mPrimaryColor = primaryColor;
        mLightPrimaryColor = lightPrimaryColor;
    }

    public int getPrimaryColor() {
        return mPrimaryColor;
    }

    public int getLightPrimaryColor() {
        return mLightPrimaryColor;
    }
}

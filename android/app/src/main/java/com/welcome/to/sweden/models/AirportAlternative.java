package com.welcome.to.sweden.models;

import android.support.annotation.StringRes;

public class AirportAlternative {
    public AirportAlternative(@StringRes int title, String img, String time, String cost) {
        this.title = title;
        this.img = img;
        this.time = time;
        this.cost = cost;
    }

    private String img;
    private String time;
    private String cost;

    @StringRes private int title;

    public int getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getTime() {
        return time;
    }

    public String getCost() {
        return cost;
    }
}

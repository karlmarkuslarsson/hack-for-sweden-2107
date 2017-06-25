package com.welcome.to.sweden.models;

public abstract class TripLocation {
    public abstract float getLatitude();

    public abstract float getLongitude();

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract String getImage();

    public abstract String getPrice();

    public abstract Integer getDuration();

    public abstract String getTag();
}

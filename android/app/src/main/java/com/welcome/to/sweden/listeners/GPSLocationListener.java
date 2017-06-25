package com.welcome.to.sweden.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.welcome.to.sweden.Storage;
import timber.log.Timber;


public class GPSLocationListener implements LocationListener {

    private final Storage mStorage;

    public GPSLocationListener(Storage storage) {
        mStorage = storage;
    }

    @Override
    public void onLocationChanged(Location location) {
        Timber.d("New location: %s", location);
        if (location != null && location.getAccuracy() <= 100) {
            mStorage.setLocation(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

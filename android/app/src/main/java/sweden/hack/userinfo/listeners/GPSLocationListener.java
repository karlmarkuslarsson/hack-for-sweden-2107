package sweden.hack.userinfo.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import sweden.hack.userinfo.Storage;
import timber.log.Timber;


/**
 * Created by Markus on 2017-01-22.
 */

public class GPSLocationListener implements LocationListener {

    private final Storage mCache;

    public GPSLocationListener(Storage cache) {
        mCache = cache;
    }

    @Override
    public void onLocationChanged(Location location) {
        Timber.d("New location: %s", location);
        if (location != null && location.getAccuracy() <= 100) {
            mCache.setLocation(location);
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

package com.welcome.to.sweden.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import com.welcome.to.sweden.Cache;
import com.welcome.to.sweden.di.InjectionContainer;
import com.welcome.to.sweden.listeners.GPSLocationListener;
import com.welcome.to.sweden.models.TripLocation;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.core.app.ActivityCompat;

public class LocationHelper {
    private static final int REQUEST_LOCATION = 12;
    private static final long MIN_TIME = 20000; //ms
    private static final float MIN_DISTANCE = 20;

    @Inject
    Cache mCache;

    @Inject
    Context mContext;

    @Inject
    public LocationHelper(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
    }

    private GPSLocationListener mLocationListener;
    private LocationManager mLocationManager;

    public void onStop() {
        removeLocationListener();
    }

    public void onStart(Activity activity) {
        setupLocationListener(activity);
    }

    @SuppressWarnings("MissingPermission")
    private void removeLocationListener() {
        if (mLocationManager != null && mLocationListener != null) {
            mLocationManager.removeUpdates(mLocationListener);
            mLocationManager = null;
            mLocationListener = null;
        }
    }

    @SuppressWarnings("MissingPermission")
    private void setupGPSManager() {
        mLocationListener = new GPSLocationListener(mCache);
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME,
                MIN_DISTANCE,
                mLocationListener);
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_TIME,
                MIN_DISTANCE,
                mLocationListener);
        Location networkLocation = mLocationManager.getLastKnownLocation(
                LocationManager.NETWORK_PROVIDER);
        Location gpsLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (networkLocation == null) {
            if (gpsLocation != null) {
                mCache.setLocation(gpsLocation);
            }
        } else {
            if (gpsLocation == null) {
                mCache.setLocation(networkLocation);
            } else {
                Location bestLocation;
                if (gpsLocation.getAccuracy() <= networkLocation.getAccuracy()) {
                    bestLocation = gpsLocation;
                } else {
                    bestLocation = networkLocation;
                }
                mCache.setLocation(bestLocation);
            }
        }
    }

    private void setupLocationListener(Activity activity) {
        if (mLocationManager == null && mLocationListener == null) {
            if (ActivityCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(activity, ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                String[] permissions = {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
                ActivityCompat.requestPermissions(activity, permissions, REQUEST_LOCATION);
            } else {
                setupGPSManager();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION:
                for (int i = 0; i < permissions.length; i++) {
                    if ((permissions[i].equals(ACCESS_COARSE_LOCATION) ||
                            permissions[i].equals(ACCESS_FINE_LOCATION)) &&
                            grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        setupGPSManager();

                    }
                }

                break;
        }
    }

    public static float getKilometerDistance(TripLocation e1, TripLocation e2) {
        return ((float) getMeterDistance(e1, e2)) / 1000.0f;
    }

    public static int getMeterDistance(@Nonnull TripLocation e1, @Nonnull TripLocation e2) {
        return getMeterDistance(
                e1.getLatitude(),
                e1.getLongitude(),
                e2.getLatitude(),
                e2.getLongitude()
        );
    }

    public static int getMeterDistance(float fromLat, float fromLng, float toLat, float toLng) {
        Location loc1 = new Location("");
        loc1.setLatitude(fromLat);
        loc1.setLongitude(fromLng);

        Location loc2 = new Location("");
        loc2.setLatitude(toLat);
        loc2.setLongitude(toLng);

        return (int) loc1.distanceTo(loc2);
    }

    public static int getTimeBetweenEvents(TripLocation preTrip, TripLocation nextTrip) {
        float kilometer = LocationHelper.getKilometerDistance(preTrip, nextTrip);
        if (kilometer < 10) {
            return (int) (kilometer * 5 * 2);
        } else {
            return (int) (kilometer * 2 * 2);
        }
    }

}

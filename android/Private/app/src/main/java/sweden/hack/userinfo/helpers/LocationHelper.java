package sweden.hack.userinfo.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import sweden.hack.userinfo.Cache;
import sweden.hack.userinfo.CustomApplication;
import sweden.hack.userinfo.listeners.GPSLocationListener;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by markus on 2017-02-12.
 */

public class LocationHelper {
    private static final int REQUEST_LOCATION = 12;
    private static final long MIN_TIME = 20000; //ms
    private static final float MIN_DISTANCE = 20;

    private final Cache mCache;

    public LocationHelper(Cache cache) {
        mCache = cache;
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
        mLocationManager = (LocationManager) CustomApplication.sharedInstance()
                .getSystemService(Context.LOCATION_SERVICE);
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
                mCache.saveLocation(gpsLocation);
            }
        } else {
            if (gpsLocation == null) {
                mCache.saveLocation(networkLocation);
            } else {
                Location bestLocation;
                if (gpsLocation.getAccuracy() <= networkLocation.getAccuracy()) {
                    bestLocation = gpsLocation;
                } else {
                    bestLocation = networkLocation;
                }
                mCache.saveLocation(bestLocation);
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

}

package sweden.hack.userinfo.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import sweden.hack.userinfo.CustomApplication;

/**
 * Created by markus on 2017-02-02.
 */

public class SharedPrefsHelper {

    private static final String SHARED_PREFERENCES_NAME = "sharedPrefs";
    private static SharedPrefsHelper sSharedInstance;
    private SharedPreferences mSharedPreferences;

    private SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
    }

    public static SharedPrefsHelper sharedInstance() {
        if (sSharedInstance == null) {
            sSharedInstance = new SharedPrefsHelper(CustomApplication.sharedInstance());
        }

        return sSharedInstance;
    }

    public String getPreference(final String name, final String defaultValue) {
        return mSharedPreferences.getString(name, defaultValue);
    }

    public Set<String> getPreference(final String name, final Set<String> defaultValue) {
        return mSharedPreferences.getStringSet(name, defaultValue);
    }

    public void setPreference(final String name, final Set<String> value) {
        SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.putStringSet(name, value);
        prefEditor.apply();
    }

    public void setPreference(final String name, final String value) {
        SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.putString(name, value);
        prefEditor.apply();
    }

    public void clearPreference(final String name) {
        setPreference(name, "");
    }

    public void clearAllPreferences() {
        SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.clear();
        prefEditor.apply();
    }

    public int getPreference(final String name, final int defaultValue) {
        return mSharedPreferences.getInt(name, defaultValue);
    }

    public void setPreference(final String key, final int value) {
        final SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.putInt(key, value);
        prefEditor.apply();
    }

    public long getPreference(final String name, final long defaultValue) {
        return mSharedPreferences.getLong(name, defaultValue);
    }

    public void setPreference(final String key, final long value) {
        final SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.putLong(key, value);
        prefEditor.apply();
    }

    public Boolean getPreference(final String key, final Boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void setPreference(final String key, final Boolean value) {
        final SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.putBoolean(key, value);
        prefEditor.apply();
    }

    public float getPreference(final String key, final float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public void setPreference(final String key, final float value) {
        final SharedPreferences.Editor prefEditor = mSharedPreferences.edit();
        prefEditor.putFloat(key, value);
        prefEditor.apply();
    }

}

package sweden.hack.userinfo;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class CustomApplication extends Application {

    private static CustomApplication sSharedInstance;

    private Cache mCache;

    public CustomApplication() {
    }

    public synchronized static CustomApplication sharedInstance() {
        if (sSharedInstance == null) {
            sSharedInstance = new CustomApplication();
        }

        return sSharedInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSharedInstance = this;
        JodaTimeAndroid.init(this);
        mCache = Cache.sharedInstance();
    }

}

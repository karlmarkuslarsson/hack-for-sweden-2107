package sweden.hack.userinfo.network.smhi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sweden.hack.userinfo.BuildConfig;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.models.smhi.Weather;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.request.CallRequest;

public class SMHIApi {

    private SMHIInterface mApi;

    private static SMHIApi sSharedInstance;

    private SMHIApi() {
        init();
    }

    public static SMHIApi sharedInstance() {

        synchronized (SMHIApi.class) {
            if (sSharedInstance == null) {
                sSharedInstance = new SMHIApi();
            }
        }

        return sSharedInstance;
    }

    public void getWeatherForLatLng(String lat,
                                    String lon,
                                    final Callback<Weather> callbackListener) {
        new CallRequest<>(mApi.getWeatherForLatLng(lat, lon), callbackListener).execute();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SMHI_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mApi = retrofit.create(SMHIInterface.class);
    }

}
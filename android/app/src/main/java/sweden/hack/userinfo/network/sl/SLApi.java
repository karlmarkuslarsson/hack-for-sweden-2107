package sweden.hack.userinfo.network.sl;


import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sweden.hack.userinfo.BuildConfig;
import sweden.hack.userinfo.Constants;
import sweden.hack.userinfo.di.InjectionContainer;
import sweden.hack.userinfo.models.sl.ClosestStations;
import sweden.hack.userinfo.models.sl.Departures;
import sweden.hack.userinfo.models.sl.Deviations;
import sweden.hack.userinfo.models.sl.SLTrip;
import sweden.hack.userinfo.models.sl.SearchStation;
import sweden.hack.userinfo.network.Callback;
import sweden.hack.userinfo.network.request.CallRequest;

public class SLApi {

    public static final int MAX_RESULTS = 50;
    public static final int RADIUS = 3000;

    private SLInterface mApi;

    @Inject
    public SLApi(InjectionContainer injectionContainer) {
        injectionContainer.inject(this);
        init();
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
                .baseUrl(Constants.SL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mApi = retrofit.create(SLInterface.class);
    }

    public void getRealTimeDepartures(String siteId,
                                      String fromMinutes,
                                      final Callback<Departures> callbackListener) {
        Call<Departures> call = mApi.getRealTimeDepartures(
                Constants.SL_DEPARTURE_KEY, siteId, fromMinutes);

        new CallRequest<>(call, callbackListener).execute();
    }

    public void getDeviations(String siteId, final Callback<Deviations> callbackListener) {
        Call<Deviations> call = mApi.getDeviations(
                Constants.SL_DEVIATIONS_KEY, removeLeadingZeros(siteId));

        new CallRequest<>(call, callbackListener).execute();
    }

    public void getAllDeviations(final Callback<Deviations> callbackListener) {
        Call<Deviations> call = mApi.getAllDeviations(Constants.SL_DEVIATIONS_KEY);

        new CallRequest<>(call, callbackListener).execute();
    }

    public void getClosestStations(double lat,
                                   double lng,
                                   int maxResults,
                                   int radius,
                                   final Callback<ClosestStations> callbackListener) {
        Call<ClosestStations> call = mApi.getClosestStations(
                Constants.SL_NEARBY_KEY, lat, lng, maxResults, radius);

        new CallRequest<>(call, callbackListener).execute();
    }

    public void getPlace(String search,
                         int maxResults,
                         final Callback<SearchStation> callbackListener) {

        Call<SearchStation> call = mApi.searchPlace(
                Constants.SL_PLACE_SEARCH_KEY, search, maxResults, true);

        new CallRequest<>(call, callbackListener).execute();
    }

    public void getTrip(String originLat,
                        String originLng,
                        String originName,
                        String destLat,
                        String destLng,
                        String destName,
                        final Callback<SLTrip> callbackListener) {

        Call<SLTrip> call = mApi.getTrip(
                Constants.SL_PLANNER, originLat, originLng, originName, destLat, destLng, destName);

        new CallRequest<>(call, callbackListener).execute();
    }

    private String removeLeadingZeros(String string) {
        return string.replaceFirst("^0+(?!$)", "");
    }

}

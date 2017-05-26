package com.welcome.to.sweden.network.sl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.welcome.to.sweden.models.sl.ClosestStations;
import com.welcome.to.sweden.models.sl.Departures;
import com.welcome.to.sweden.models.sl.Deviations;
import com.welcome.to.sweden.models.sl.SLTrip;
import com.welcome.to.sweden.models.sl.SearchStation;

public interface SLInterface {

    @GET("realtimedeparturesV4.json")
    Call<Departures> getRealTimeDepartures(
            @Query("key") String key,
            @Query("siteid") String siteId,
            @Query("timewindow") String fromMinutes);

    @GET("deviations.json")
    Call<Deviations> getAllDeviations(
            @Query("key") String key
    );

    @GET("deviations.json")
    Call<Deviations> getDeviations(
            @Query("key") String key,
            @Query("siteid") String siteId
    );

    @GET("nearbystops.json")
    Call<ClosestStations> getClosestStations(
            @Query("key") String key,
            @Query("originCoordLat") double lat,
            @Query("originCoordLong") double lon,
            @Query("maxresults") int maxResults,
            @Query("radius") int radius //meter
    );

    @GET("typeahead.json")
    Call<SearchStation> searchPlace(
            @Query("key") String key,
            @Query("searchstring") String searchString,
            @Query("maxresults") int maxResults,
            @Query("StationsOnly") boolean stationsOnly);

    @GET("TravelplannerV2/trip.json")
    Call<SLTrip> getTrip(
            @Query("key") String key,
            @Query("originCoordLat") String originLat,
            @Query("originCoordLong") String originLng,
            @Query("originCoordName") String originName,
            @Query("destCoordLat") String destLat,
            @Query("destCoordLong") String destLng,
            @Query("destCoordName") String destName);

}

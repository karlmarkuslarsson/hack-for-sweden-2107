package com.welcome.to.sweden.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import com.welcome.to.sweden.models.cards.phrases.Phrases;

public interface PhrasesInterface {

    @GET("phrases")
    Call<Phrases> getPhrases();
}

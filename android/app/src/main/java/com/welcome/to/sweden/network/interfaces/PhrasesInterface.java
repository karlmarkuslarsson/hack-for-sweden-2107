package sweden.hack.userinfo.network.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import sweden.hack.userinfo.models.cards.phrases.Phrases;

public interface PhrasesInterface {

    @GET("phrases")
    Call<Phrases> getPhrases();
}

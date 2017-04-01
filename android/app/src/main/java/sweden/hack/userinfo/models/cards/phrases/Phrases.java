package sweden.hack.userinfo.models.cards.phrases;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sweden.hack.userinfo.models.cards.CardComponent;

public class Phrases extends CardComponent {

    @SerializedName("phrases")
    private List<Phrase> mPhrases;

    public List<Phrase> getPhrases() {
        return mPhrases;
    }
}

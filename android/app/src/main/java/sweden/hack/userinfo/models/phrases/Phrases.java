package sweden.hack.userinfo.models.phrases;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sweden.hack.userinfo.models.CardComponent;

public class Phrases extends CardComponent {

    @SerializedName("phrases")
    private List<String> mPhrases;

    public String getPhrases() {
        StringBuilder sb = new StringBuilder();
        for (String str : mPhrases) {
            sb.append(str).append("\n");
        }
        return sb.toString();
    }
}

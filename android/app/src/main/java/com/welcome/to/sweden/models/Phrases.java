package com.welcome.to.sweden.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Phrases {

    @SerializedName("phrases")
    private List<Phrase> mPhrases;

    public List<Phrase> getPhrases() {
        return mPhrases;
    }
}

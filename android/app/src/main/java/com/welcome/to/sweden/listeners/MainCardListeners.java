package com.welcome.to.sweden.listeners;

import com.welcome.to.sweden.models.cards.base.Card;

public class MainCardListeners {

    private MainCardListeners() {}

    public static MainCardListener dummy() {
        return new MainCardListener() {
            @Override
            public void onCardClick(Card card) {}

            @Override
            public void dismissCard(Card card) {}
        };
    }
}

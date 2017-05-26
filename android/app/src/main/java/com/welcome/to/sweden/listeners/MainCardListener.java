package com.welcome.to.sweden.listeners;

import com.welcome.to.sweden.objects.main.base.Card;

public interface MainCardListener {
    public void onCardClick(Card card);

    void dismissCard(Card card);
}

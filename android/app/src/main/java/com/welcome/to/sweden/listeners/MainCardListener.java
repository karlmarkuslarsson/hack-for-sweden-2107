package com.welcome.to.sweden.listeners;

import com.welcome.to.sweden.objects.main.base.MainCard;

public interface MainCardListener {
    public void onCardClick(MainCard card);

    void dismissCard(MainCard card);
}

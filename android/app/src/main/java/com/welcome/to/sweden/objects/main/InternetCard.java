package com.welcome.to.sweden.objects.main;

import com.welcome.to.sweden.objects.main.base.MainCard;

public class InternetCard extends MainCard {
    @Override
    public int getViewType() {
        return MainCard.TYPE_INTERNET;
    }

    public String getInternetInfo() {
        return "Buying a prepaid SIM card is easy. You can get one at most supermarkets, in newsagents and convenience stores (like Pressbyrån and 7 Eleven) and in the phone companies own stores. Just put it in, and you are good to go.";
    }

}

package sweden.hack.userinfo.listeners;

import sweden.hack.userinfo.objects.main.base.MainCard;

public interface MainCardListener {
    public void onCardClick(MainCard card);

    void dismissCard(MainCard card);
}

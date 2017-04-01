package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.models.cards.CurrentCurrency;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class CurrencyCard extends MainCard {

    private final CurrentCurrency mCurrentCurrency;

    public CurrencyCard(CurrentCurrency currentCurrency) {
        mCurrentCurrency = currentCurrency;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_CURRENCY;
    }

    public String getCurrencyString() {
        return mCurrentCurrency.getValue();
    }

}

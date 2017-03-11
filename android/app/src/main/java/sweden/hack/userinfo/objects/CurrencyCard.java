package sweden.hack.userinfo.objects;

import sweden.hack.userinfo.models.currency.Currency;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class CurrencyCard extends MainCard {

    private final Currency mCurrency;

    public CurrencyCard(Currency currency) {
        mCurrency = currency;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_CURRENCY;
    }

    public String getCurrencyString() {
        return mCurrency.getValue();
    }

}

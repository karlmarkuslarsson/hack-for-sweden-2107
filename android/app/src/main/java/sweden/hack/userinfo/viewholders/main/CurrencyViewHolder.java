package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.CurrencyCard;

public class CurrencyViewHolder extends MainViewHolder<CurrencyCard> {

    private final TextView mTextCurrency;

    public CurrencyViewHolder(View root) {
        super(root);
        mTextCurrency = (TextView) root.findViewById(R.id.text_currency);
    }

    @Override
    public void init(CurrencyCard card, MainCardListener listener) {
        mTextCurrency.setText(card.getCurrencyString());
    }
}

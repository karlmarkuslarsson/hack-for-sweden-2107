package com.welcome.to.sweden.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.objects.main.CurrencyCard;

public class CurrencyViewHolder extends MainViewHolder<CurrencyCard> {

    @BindView(R.id.text_currency)
    TextView mTextCurrency;

    public CurrencyViewHolder(View root) {
        super(root);
        ButterKnife.bind(this, root);
    }

    @Override
    public void init(CurrencyCard card, MainCardListener listener) {
        mTextCurrency.setText(card.getCurrencyString());
    }

}

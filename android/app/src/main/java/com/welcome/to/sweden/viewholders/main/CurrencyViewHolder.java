package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.CurrencyCard;

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

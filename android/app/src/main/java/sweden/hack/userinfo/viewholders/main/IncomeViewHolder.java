package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.objects.main.IncomeCard;

public class IncomeViewHolder extends MainViewHolder<IncomeCard> {


    @BindView(R.id.text_income)
    TextView mTextView;

    public IncomeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(IncomeCard card, MainCardListener listener) {
        List<Income> incomes = card.getIncomes();
        mTextView.setText(String.format("Age %s," + "\n" + " Median income %s", incomes.get(0).getAge(), incomes.get(0).getMedianIncome()));
    }

}

package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;


import java.util.List;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.objects.main.IncomeCard;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class IncomeViewHolder extends MainViewHolder {

    private final TextView mTextView;

    public IncomeViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text_income);
    }

    @Override
    public void init(MainCard card, MainCardListener listener) {
        IncomeCard incomeCard = (IncomeCard) card;
        List<Income> incomes = incomeCard.getIncomes();
        mTextView.setText(String.format("Age %s,"+ "\n" +" Median income %s", incomes.get(0).getAge(), incomes.get(0).getMedianIncome()));
    }
}

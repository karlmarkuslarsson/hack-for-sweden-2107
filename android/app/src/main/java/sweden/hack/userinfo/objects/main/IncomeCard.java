package sweden.hack.userinfo.objects.main;

import java.util.List;

import sweden.hack.userinfo.models.income.Income;
import sweden.hack.userinfo.objects.main.base.MainCard;

public class IncomeCard extends MainCard {
    private final List<Income> mIncomes;

    public IncomeCard(List<Income> incomes) {
        mIncomes = incomes;
    }

    @Override
    public int getViewType() {
        return MainCard.TYPE_INCOME;
    }

    public List<Income> getIncomes() {
        return mIncomes;
    }
}

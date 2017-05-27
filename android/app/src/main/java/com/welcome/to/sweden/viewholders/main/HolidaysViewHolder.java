package com.welcome.to.sweden.viewholders.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.models.Holiday;
import com.welcome.to.sweden.models.cards.HolidaysCard;

public class HolidaysViewHolder extends MainViewHolder<HolidaysCard> {

    @BindView(R.id.content)
    ViewGroup mContent;

    public HolidaysViewHolder(View root) {
        super(root);
        ButterKnife.bind(this, root);
    }

    @Override
    public void init(HolidaysCard card, MainCardListener listener) {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        List<Holiday> holidays = card.getHolidays();
        mContent.removeAllViews();
        for (int i = 0; i < holidays.size(); i++) {
            Holiday holiday = holidays.get(i);
            LinearLayout linearLayout = new LinearLayout(itemView.getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setWeightSum(2);
            addView(linearLayout, holiday, inflater);
            i++;
            if (i < holidays.size()) {
                addView(linearLayout, holidays.get(i), inflater);
            }

            mContent.addView(linearLayout);
        }
    }

    private void addView(LinearLayout linearLayout, Holiday holiday, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.card_holidays_part, linearLayout, false);
        TextView.class.cast(view.findViewById(R.id.text1)).setText(holiday.getName());
        TextView.class.cast(view.findViewById(R.id.text2)).setText(holiday.getWeekday());
        TextView.class.cast(view.findViewById(R.id.text3)).setText(holiday.getDate());
        linearLayout.addView(view);
    }

}

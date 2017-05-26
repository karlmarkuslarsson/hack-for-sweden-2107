package com.welcome.to.sweden.viewholders.main;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.welcome.to.sweden.R;
import com.welcome.to.sweden.listeners.MainCardListener;
import com.welcome.to.sweden.objects.main.NextDayDivider;

public class NextDayViewHolder extends MainViewHolder<NextDayDivider> {

    @BindView(R.id.text)
    TextView mTextView;

    public NextDayViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void init(NextDayDivider card, MainCardListener listener) {
        mTextView.setText("Day " + (card.getCounter() + 1));
    }

}

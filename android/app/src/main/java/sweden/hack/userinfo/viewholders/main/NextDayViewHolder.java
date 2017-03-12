package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.NextDayDivider;

public class NextDayViewHolder extends MainViewHolder<NextDayDivider> {

    private final TextView mTextView;

    public NextDayViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text);
    }

    @Override
    public void init(NextDayDivider card, MainCardListener listener) {
        mTextView.setText("Day " + (card.getCounter() + 1));
    }
}

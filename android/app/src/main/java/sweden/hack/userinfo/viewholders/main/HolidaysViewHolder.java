package sweden.hack.userinfo.viewholders.main;

import android.view.View;
import android.widget.TextView;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.HolidaysCard;

public class HolidaysViewHolder extends MainViewHolder<HolidaysCard>{
    private final TextView mTextHolidays;

    public HolidaysViewHolder(View root) {
        super(root);
        mTextHolidays = (TextView) root.findViewById(R.id.text_holidays);
    }

    @Override
    public void init(HolidaysCard card, MainCardListener listener) {
        mTextHolidays.setText(card.getHolidays().getHolidays().get(0).getName());
    }
}

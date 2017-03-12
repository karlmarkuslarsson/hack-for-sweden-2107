package sweden.hack.userinfo.viewholders.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.models.holdays.Holiday;
import sweden.hack.userinfo.objects.main.HolidaysCard;

public class HolidaysViewHolder extends MainViewHolder<HolidaysCard>{

    private final ViewGroup mContent;

    public HolidaysViewHolder(View root) {
        super(root);
        mContent = (ViewGroup) root.findViewById(R.id.content);
    }

    @Override
    public void init(HolidaysCard card, MainCardListener listener) {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        List<Holiday> holidays = card.getHolidays().getHolidays();
        mContent.removeAllViews();
        for (Holiday holiday : holidays) {
            View view = inflater.inflate(R.layout.card_holidays_part, mContent, false);
            TextView.class.cast(view.findViewById(R.id.text1)).setText(holiday.getName());
            TextView.class.cast(view.findViewById(R.id.text2)).setText(holiday.getWeekday());
            TextView.class.cast(view.findViewById(R.id.text3)).setText(holiday.getDate());
            mContent.addView(view);
        }
    }
}

package sweden.hack.userinfo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sweden.hack.userinfo.R;
import sweden.hack.userinfo.listeners.MainCardListener;
import sweden.hack.userinfo.objects.main.base.MainCard;
import sweden.hack.userinfo.viewholders.main.GenderViewHolder;
import sweden.hack.userinfo.viewholders.main.MainViewHolder;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private final MainCardListener mListener;
    private List<MainCard> mCards;

    public MainRecyclerViewAdapter(MainCardListener listener) {
        mCards = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return mCards.get(position).getViewType();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view holder associated with the view type.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case MainCard.TYPE_GENDER:
                return new GenderViewHolder(inflater.inflate(R.layout.card_gender, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.init(mCards.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public void addCard(MainCard card) {
        mCards.add(card);
        notifyItemInserted(mCards.size());
    }

    public void reset() {
        mCards.clear();
        notifyDataSetChanged();
    }
}

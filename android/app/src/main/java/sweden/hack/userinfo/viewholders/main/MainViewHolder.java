package sweden.hack.userinfo.viewholders.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import sweden.hack.userinfo.listeners.MainCardListener;

public abstract class MainViewHolder<T> extends RecyclerView.ViewHolder {

    public MainViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void init(T card, MainCardListener listener);

    public void clearAnimation() {
        itemView.clearAnimation();
    }

}

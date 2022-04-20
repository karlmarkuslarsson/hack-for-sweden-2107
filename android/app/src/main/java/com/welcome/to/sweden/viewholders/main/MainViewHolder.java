package com.welcome.to.sweden.viewholders.main;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.welcome.to.sweden.listeners.MainCardListener;

public abstract class MainViewHolder<T> extends RecyclerView.ViewHolder {

    public MainViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void init(T card, MainCardListener listener);

    public void clearAnimation() {
        itemView.clearAnimation();
    }

    public RecyclerView.LayoutParams getLayoutParams() {
        return (RecyclerView.LayoutParams) itemView.getLayoutParams();
    }

}

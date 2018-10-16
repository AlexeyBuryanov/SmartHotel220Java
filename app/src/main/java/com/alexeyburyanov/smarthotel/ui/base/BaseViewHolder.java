package com.alexeyburyanov.smarthotel.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);
}
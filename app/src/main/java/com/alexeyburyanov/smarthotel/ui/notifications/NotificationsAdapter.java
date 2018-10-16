package com.alexeyburyanov.smarthotel.ui.notifications;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.Notification;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private List<Notification> _data;
    private LayoutInflater _inflater;
    private NotificationsAdapter.ItemClickListener _clickListener;
    private int _selectedPos = -1;
    private Context _context;

    public NotificationsAdapter(Context context, List<Notification> data) {
        _inflater = LayoutInflater.from(context);
        _data = data;
        _context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _inflater.inflate(R.layout.item_notifications, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification item = _data.get(position);
        String nts = "";
        int icRes = 0;
        switch (item.get_type()) {
            case Room:
                nts = "Номер";
                icRes = R.mipmap.ic_room_2;
                break;
            case Hotel:
                nts = "Отель";
                icRes = R.mipmap.ic_hotel_2;
                break;
            case BeGreen:
                nts = "Уборка";
                icRes = R.mipmap.ic_be_green_2;
                break;
            case Other:
                nts = "Другое";
                icRes = R.mipmap.ic_other_2;
                break;
        } // switch
        holder.ivIcon.setImageResource(icRes);
        holder.tvType.setText(nts);
        holder.tvText.setText(item.get_text());
        holder.btnBasket.setOnClickListener(v -> {
            _data.remove(position);
            _clickListener.onItemClick(holder.itemView, position);
            notifyDataSetChanged();
        });
    }

    public void setAnimation(View view, boolean inLeft) {
        if (inLeft) {
            Animation animation = AnimationUtils.loadAnimation(_context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
        } else {
            Animation animation = AnimationUtils.loadAnimation(_context, android.R.anim.slide_out_right);
            view.startAnimation(animation);
        }
    }

    public List<Notification> getData() {
        return _data;
    }

    @Override
    public int getItemCount() { return _data.size(); }
    public Notification getItem(int id) { return _data.get(id); }
    public void setClickListener(NotificationsAdapter.ItemClickListener itemClickListener) { _clickListener = itemClickListener; }

    public interface ItemClickListener { void onItemClick(View view, int position); }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivIcon;
        TextView tvType;
        TextView tvText;
        ImageButton btnBasket;

        ViewHolder(View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvType = itemView.findViewById(R.id.tvType);
            tvText = itemView.findViewById(R.id.tvText);
            btnBasket = itemView.findViewById(R.id.btnBasket);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (_clickListener != null) {
                _clickListener.onItemClick(view, getAdapterPosition());
                _selectedPos = getAdapterPosition();
                notifyDataSetChanged();
            }
        }
    }
}

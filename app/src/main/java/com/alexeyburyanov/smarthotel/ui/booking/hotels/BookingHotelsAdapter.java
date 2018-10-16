package com.alexeyburyanov.smarthotel.ui.booking.hotels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.BookingHotelsItem;

import java.util.List;

/**
 * Created by Alexey on 20.03.2018.
 */
public class BookingHotelsAdapter extends RecyclerView.Adapter<BookingHotelsAdapter.ViewHolder> {

    private List<BookingHotelsItem> _data;
    private LayoutInflater _inflater;
    private BookingHotelsAdapter.ItemClickListener _clickListener;
    private int _selectedPos = RecyclerView.NO_POSITION;

    BookingHotelsAdapter(Context context, List<BookingHotelsItem> data) {
        _inflater = LayoutInflater.from(context);
        _data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _inflater.inflate(R.layout.item_booking_hotels, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingHotelsItem item = _data.get(position);
        holder.ivPicture.setImageResource(item.get_picture());
        holder.tvFullCity.setText(item.get_fullCity());
        holder.tvNameHotel.setText(item.get_nameHotel());
        holder.tvPrice.setText(String.valueOf(item.get_price()));
        holder.rbRatingHotel.setRating(item.get_rating());
    }

    public List<BookingHotelsItem> getData() {
        return _data;
    }

    @Override
    public int getItemCount() { return _data.size(); }
    public BookingHotelsItem getItem(int id) { return _data.get(id); }
    public void setClickListener(ItemClickListener itemClickListener) { _clickListener = itemClickListener; }

    public interface ItemClickListener { void onItemClick(View view, int position); }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivPicture;
        TextView tvFullCity;
        TextView tvNameHotel;
        TextView tvPrice;
        RatingBar rbRatingHotel;

        ViewHolder(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvFullCity = itemView.findViewById(R.id.tvFullCity);
            tvNameHotel = itemView.findViewById(R.id.tvNameHotel);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            rbRatingHotel = itemView.findViewById(R.id.rbRatingHotel);
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
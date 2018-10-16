package com.alexeyburyanov.smarthotel.ui.booking.hotel.reviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.ReviewsItem;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alexey on 24.03.2018.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private List<ReviewsItem> _data;
    private LayoutInflater _inflater;
    private ReviewsAdapter.ItemClickListener _clickListener;
    private int _selectedPos = RecyclerView.NO_POSITION;
    private SimpleDateFormat _simpleDateFormat = new SimpleDateFormat("dd MMMM YYYY", new Locale("ru"));

    public ReviewsAdapter(Context context, List<ReviewsItem> data) {
        _inflater = LayoutInflater.from(context);
        _data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _inflater.inflate(R.layout.item_booking_reviews, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewsItem item = _data.get(position);
        holder.tvReviewer.setText(item.get_reviewer());
        holder.tvTypeRoom.setText(item.get_typeRoom());
        holder.tvDate.setText( _simpleDateFormat.format(item.get_date()));
        holder.rbRating.setRating(item.get_rating());
        holder.tvReview.setText(item.get_review());
    }

    public List<ReviewsItem> getData() {
        return _data;
    }

    @Override
    public int getItemCount() { return _data.size(); }
    public ReviewsItem getItem(int id) { return _data.get(id); }
    public void setClickListener(ItemClickListener itemClickListener) { _clickListener = itemClickListener; }

    public interface ItemClickListener { void onItemClick(View view, int position); }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvReviewer;
        TextView tvTypeRoom;
        TextView tvDate;
        RatingBar rbRating;
        TextView tvReview;

        ViewHolder(View itemView) {
            super(itemView);
            tvReviewer = itemView.findViewById(R.id.tvReviewer);
            tvTypeRoom = itemView.findViewById(R.id.tvTypeRoom);
            tvDate = itemView.findViewById(R.id.tvDate);
            rbRating = itemView.findViewById(R.id.rbRating);
            tvReview = itemView.findViewById(R.id.tvReview);
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
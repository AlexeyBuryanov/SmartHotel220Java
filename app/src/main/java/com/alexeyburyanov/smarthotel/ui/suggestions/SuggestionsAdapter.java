package com.alexeyburyanov.smarthotel.ui.suggestions;

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
import com.alexeyburyanov.smarthotel.data.models.items.SuggestionItem;

import java.util.List;

/**
 * Created by Alexey on 08.04.2018.
 */
public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.ViewHolder> {

    private List<SuggestionItem> _data;
    private LayoutInflater _inflater;
    private SuggestionsAdapter.ItemClickListener _clickListener;
    private int _selectedPos = RecyclerView.NO_POSITION;

    public SuggestionsAdapter(Context context, List<SuggestionItem> data) {
        _inflater = LayoutInflater.from(context);
        _data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _inflater.inflate(R.layout.item_suggestions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SuggestionItem item = _data.get(position);
        holder.ivPicture.setImageResource(item.get_picture());
        holder.tvName.setText(item.get_name());
        holder.tvDescription.setText(item.get_description());
        holder.rbRating.setRating(item.get_rating());
        holder.tvNumRating.setText(String.format("%s голос", String.valueOf(item.get_numRating())));
    }

    public List<SuggestionItem> getData() {
        return _data;
    }

    @Override
    public int getItemCount() { return _data.size(); }
    public SuggestionItem getItem(int id) { return _data.get(id); }
    public void setClickListener(ItemClickListener itemClickListener) { _clickListener = itemClickListener; }

    public interface ItemClickListener { void onItemClick(View view, int position); }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivPicture;
        TextView tvName;
        TextView tvDescription;
        RatingBar rbRating;
        TextView tvNumRating;

        ViewHolder(View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            rbRating = itemView.findViewById(R.id.rbRating);
            tvNumRating = itemView.findViewById(R.id.tvNumRating);
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

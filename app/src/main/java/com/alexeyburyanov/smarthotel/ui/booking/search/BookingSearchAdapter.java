package com.alexeyburyanov.smarthotel.ui.booking.search;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.BookingSearchItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexey on 14.03.2018.
 */
public class BookingSearchAdapter extends RecyclerView.Adapter<BookingSearchAdapter.ViewHolder>
        implements Filterable {

    private List<BookingSearchItem> _data;
    private List<BookingSearchItem> _dataBackUp;
    private List<BookingSearchItem> _dataFiltered;
    private LayoutInflater _inflater;
    private ItemClickListener _clickListener;
    private int _selectedPos = RecyclerView.NO_POSITION;
    private int _selectedColor = Color.parseColor("#BBDEFB");

    BookingSearchAdapter(Context context, List<BookingSearchItem> data) {
        _inflater = LayoutInflater.from(context);
        _data = data;
        _dataFiltered = data;
        _dataBackUp = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _inflater.inflate(R.layout.item_booking_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingSearchItem item = _data.get(position);
        holder.tvWhere.setText(item.get_where());

        if (_selectedPos == position) {
            holder.itemView.setBackgroundColor(_selectedColor);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public List<BookingSearchItem> getData() {
        return _data;
    }

    @Override
    public int getItemCount() { return _data.size(); }
    public BookingSearchItem getItem(int id) { return _data.get(id); }
    public void setClickListener(ItemClickListener itemClickListener) { _clickListener = itemClickListener; }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    _dataFiltered = _dataBackUp;
                } else {
                    List<BookingSearchItem> filteredList = new LinkedList<>();
                    for (BookingSearchItem row : _data) {
                        if (row.get_where().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        } // if
                    } // for
                    _dataFiltered = filteredList;
                } // if-else
                FilterResults filterResults = new FilterResults();
                filterResults.values = _dataFiltered;
                return filterResults;
            } // performFiltering
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                _data = (List<BookingSearchItem>)results.values;
                notifyDataSetChanged();
            } // publishResults
        };
    } // getFilter

    public interface ItemClickListener { void onItemClick(View view, int position); }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvWhere;

        ViewHolder(View itemView) {
            super(itemView);
            tvWhere = itemView.findViewById(R.id.tvWhere);
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
package com.alexeyburyanov.smarthotel.ui.booking.hotel.reviews;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alexeyburyanov.smarthotel.BR;
import com.alexeyburyanov.smarthotel.R;
import com.alexeyburyanov.smarthotel.data.models.items.ReviewsItem;
import com.alexeyburyanov.smarthotel.databinding.FragmentReviewsBinding;
import com.alexeyburyanov.smarthotel.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Alexey on 23.03.2018.
 */
public class ReviewsFragment extends BaseFragment<FragmentReviewsBinding, ReviewsViewModel>
        implements ReviewsNavigator, ReviewsAdapter.ItemClickListener {

    @Inject ViewModelProvider.Factory _viewModelFactory;
    @Inject ReviewsAdapter _adapter;
    @Inject LinearLayoutManager _layoutManager;
    FragmentReviewsBinding _fragmentReviewsBinding;

    private final static String REVIEW_ITEM = "REVIEW_ITEM";
    private ReviewsViewModel _reviewsViewModel;
    private ReviewsItem _selectedItem;
    private ReviewsItem _review;

    public static ReviewsFragment newInstance(ReviewsItem review) {
        Bundle args = new Bundle();
        args.putSerializable(REVIEW_ITEM, review);
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _reviewsViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp();
    }

    private void setUp() {
        _fragmentReviewsBinding = getViewDataBinding();

        Bundle args = getArguments();
        if (args != null) {
            _review = (ReviewsItem)args.getSerializable(REVIEW_ITEM);
            _adapter.getData().add(_review);
            _adapter.setClickListener(this);
            _fragmentReviewsBinding.recyclerViewReviews.setAdapter(_adapter);
            _layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            _fragmentReviewsBinding.recyclerViewReviews.setLayoutManager(_layoutManager);
            _fragmentReviewsBinding.recyclerViewReviews.setItemAnimator(new DefaultItemAnimator());
            _fragmentReviewsBinding.recyclerViewReviews.setAdapter(_adapter);
        }
    }

    @Override
    public void onItemClick(View view, int position) { _selectedItem = _adapter.getItem(position); }

    @Override
    public ReviewsViewModel getViewModel() {
        _reviewsViewModel = ViewModelProviders.of(this, _viewModelFactory).get(ReviewsViewModel.class);
        return _reviewsViewModel;
    }
    @Override
    public int getBindingVariable() { return BR.viewModel; }
    @Override
    public int getLayoutId() { return R.layout.fragment_reviews; }
}
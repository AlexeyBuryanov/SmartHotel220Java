package com.alexeyburyanov.smarthotel.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Alexey Buryanov 19.02.2018.
 */
public final class BindingUtils {

    private BindingUtils() {
        // Этот класс утилиты не является общедоступным
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}

package com.alexeyburyanov.smarthotel.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.content.ContextCompat;

import com.alexeyburyanov.smarthotel.R;

import io.reactivex.annotations.NonNull;

/**
 * Created by amitshekhar on 07/07/17.
 */
public final class ViewUtils {

    private ViewUtils() {
        // Этот класс не является общедоступным
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static Bitmap blur(Bitmap b, Context context) {
        RenderScript rs = RenderScript.create(context);
        Allocation overlayAlloc = Allocation.createFromBitmap(rs, b);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());
        blur.setInput(overlayAlloc);
        blur.setRadius(25.0f);
        blur.forEach(overlayAlloc);
        overlayAlloc.copyTo(b);
        return b;
    }

    public static float getTextHeight(Paint paint) {
        Paint.FontMetrics m = paint.getFontMetrics();
        return m.bottom - m.top;
    }

    public static float getTextRectWidth(Paint paint, String content) {
        Rect rect = new Rect();
        paint.getTextBounds(content, 0, content.length(), rect);
        return (float) rect.width();
    }

    public static float getTextRectHeight(Paint paint, String content) {
        Rect rect = new Rect();
        paint.getTextBounds(content, 0, content.length(), rect);
        return (float) rect.height();
    }
}
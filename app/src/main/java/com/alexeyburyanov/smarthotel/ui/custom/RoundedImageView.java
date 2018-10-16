package com.alexeyburyanov.smarthotel.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by amitshekhar on 08/07/17
 * Modified by Alexey Buryanov on 23.02.2018
 * Круглое изображение.
 */
public class RoundedImageView extends AppCompatImageView {

    private static final String TAG = "RoundedImageView";
    private int _width, _height;
    private Drawable _drawable;
    private Bitmap _bitmap;

    public RoundedImageView(Context context) {
        super(context);
        setUp();
    }
    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }
    public RoundedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp() {
        _drawable = getDrawable();
        _width = getWidth();
        _height = getHeight();

        if (_drawable instanceof BitmapDrawable) {
            Bitmap b = ((BitmapDrawable) _drawable).getBitmap();
            _bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        } else if (_drawable instanceof ColorDrawable) {
            _bitmap = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(_bitmap);
            c.drawColor(((ColorDrawable) _drawable).getColor());
        } // if
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            _drawable = getDrawable();
            _width = getWidth();
            _height = getHeight();

            if (_drawable == null) {
                return;
            } // if
            if (_width == 0 || _height == 0) {
                return;
            } // if
            if (_width <= 0 || _height <= 0) {
                return;
            } // if

            Bitmap roundBitmap = getRoundedCroppedBitmap(_bitmap, Math.min(_width, _height));
            canvas.drawBitmap(roundBitmap, 0, 0, null);
        } catch (Exception e) {
            Log.e(TAG, "onDraw Exception", e);
        } // try-catch
    }

    private Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;

        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius) {
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        } else {
            finalBitmap = bitmap;
        } // if-else

        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(), finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(), finalBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() / 2, finalBitmap.getHeight() / 2, finalBitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }
}